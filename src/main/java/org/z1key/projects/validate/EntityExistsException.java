package org.z1key.projects.validate;

/**
 * Created by User on 14.04.2017.
 */
public class EntityExistsException extends RuntimeException {
    public EntityExistsException(String message) {
        super(message);
    }
}
