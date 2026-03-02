package com.bikerental.model;

/**
 * Base entity class demonstrating inheritance.
 * Implements FileStorable (abstraction). Information hiding: id is private, access only via getters/setters.
 * All storable entities in the system extend this class.
 */
public abstract class BaseEntity implements FileStorable {
    private String id;

    public BaseEntity() {
    }

    public BaseEntity(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * Polymorphism: each entity type defines its own serialization format for file storage.
     */
    public abstract String toFileLine();

    /**
     * Parse a line from file into entity (implemented by subclasses).
     */
    public static BaseEntity fromFileLine(String line) {
        return null;
    }
}
