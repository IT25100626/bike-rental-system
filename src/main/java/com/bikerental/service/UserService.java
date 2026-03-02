package com.bikerental.service;

import com.bikerental.model.User;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * File handling: data storage via file read/write only (no database).
 * - Read: Files.lines(), BufferedReader-style line-by-line read from data/users.txt
 * - Write: BufferedWriter for Create (append) and Update/Delete (full file rewrite)
 * CRUD: Create (append line), Read (findAll/search), Update (read-all, modify, writeAll), Delete (read-all, remove, writeAll).
 */
public class UserService {
    private static final String DATA_DIR = "data";
    private static final String USERS_FILE = "users.txt";
    private final Path usersPath;

    public UserService() {
        // Use system property "data.dir" if set (e.g. Tomcat), else project-relative "data"
        String dataDir = System.getProperty("data.dir");
        Path base = dataDir != null ? Paths.get(dataDir) : Paths.get(System.getProperty("user.dir"), DATA_DIR);
        this.usersPath = base.resolve(USERS_FILE);
        ensureDataDirAndFile();
    }

    private void ensureDataDirAndFile() {
        try {
            Files.createDirectories(usersPath.getParent());
            if (!Files.exists(usersPath)) {
                Files.createFile(usersPath);
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not create data file: " + usersPath, e);
        }
    }

    /**
     * CREATE: Add a new user to the file.
     */
    public User create(User user) throws IOException {
        if (user.getId() == null || user.getId().trim().isEmpty()) {
            user.setId(UUID.randomUUID().toString());
        }
        if (findByEmail(user.getEmail()) != null) {
            return null; // email already exists
        }
        try (BufferedWriter writer = Files.newBufferedWriter(usersPath, StandardOpenOption.APPEND)) {
            writer.write(user.toFileLine());
            writer.newLine();
        }
        return user;
    }

    /**
     * READ: Get all users from file.
     */
    public List<User> findAll() throws IOException {
        if (!Files.exists(usersPath)) return new ArrayList<>();
        return Files.lines(usersPath)
                .map(String::trim)
                .filter(line -> !line.isEmpty())
                .map(User::fromFileLine)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    /**
     * READ: Find user by ID.
     */
    public User findById(String id) throws IOException {
        return findAll().stream()
                .filter(u -> id.equals(u.getId()))
                .findFirst()
                .orElse(null);
    }

    /**
     * READ: Search user by email.
     */
    public User findByEmail(String email) throws IOException {
        if (email == null) return null;
        return findAll().stream()
                .filter(u -> email.equalsIgnoreCase(u.getEmail()))
                .findFirst()
                .orElse(null);
    }

    /**
     * READ: Search users by name or email (keyword).
     */
    public List<User> search(String keyword) throws IOException {
        if (keyword == null || keyword.trim().isEmpty()) {
            return findAll();
        }
        String k = keyword.trim().toLowerCase();
        return findAll().stream()
                .filter(u -> (u.getFullName() != null && u.getFullName().toLowerCase().contains(k))
                        || (u.getEmail() != null && u.getEmail().toLowerCase().contains(k))
                        || (u.getPhone() != null && u.getPhone().contains(keyword.trim())))
                .collect(Collectors.toList());
    }

    /**
     * UPDATE: Update an existing user in the file.
     */
    public boolean update(User user) throws IOException {
        List<User> all = findAll();
        boolean found = false;
        for (int i = 0; i < all.size(); i++) {
            if (user.getId().equals(all.get(i).getId())) {
                all.set(i, user);
                found = true;
                break;
            }
        }
        if (!found) return false;
        writeAll(all);
        return true;
    }

    /**
     * DELETE: Remove a user from the file.
     */
    public boolean delete(String id) throws IOException {
        List<User> all = findAll();
        boolean removed = all.removeIf(u -> id.equals(u.getId()));
        if (removed) {
            writeAll(all);
        }
        return removed;
    }

    private void writeAll(List<User> users) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(usersPath)) {
            for (User u : users) {
                writer.write(u.toFileLine());
                writer.newLine();
            }
        }
    }
}
