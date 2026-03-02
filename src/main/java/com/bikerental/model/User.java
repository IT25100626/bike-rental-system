package com.bikerental.model;

/**
 * User model: encapsulation (private fields, getters/setters), inheritance (extends BaseEntity),
 * polymorphism (overrides toFileLine() for file serialization). Information hiding: no direct field access.
 */
public class User extends BaseEntity {
    private String email;
    private String fullName;
    private String phone;
    private String role;  // e.g., CUSTOMER, RIDER, ADMIN

    public static final String DELIMITER = "|||";

    public User() {
        super();
    }

    public User(String id, String email, String fullName, String phone, String role) {
        super(id);
        this.email = email;
        this.fullName = fullName;
        this.phone = phone;
        this.role = role;
    }

    @Override
    public String toFileLine() {
        return getId() + DELIMITER + email + DELIMITER + fullName + DELIMITER + phone + DELIMITER + role;
    }

    public static User fromFileLine(String line) {
        if (line == null || line.trim().isEmpty()) return null;
        String[] parts = line.split("\\|\\|\\|", -1);
        if (parts.length < 5) return null;
        User u = new User();
        u.setId(parts[0].trim());
        u.setEmail(parts[1].trim());
        u.setFullName(parts[2].trim());
        u.setPhone(parts[3].trim());
        u.setRole(parts[4].trim());
        return u;
    }

    // Getters and setters (encapsulation)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
