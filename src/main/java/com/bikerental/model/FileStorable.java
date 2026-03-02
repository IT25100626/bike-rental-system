package com.bikerental.model;

/**
 * Abstraction: interface for entities that can be serialized to/from file storage.
 * Enables polymorphic file handling in UserService (treats any FileStorable uniformly).
 */
public interface FileStorable {

    /**
     * Serialize this entity to a single line for file write.
     */
    String toFileLine();
}
