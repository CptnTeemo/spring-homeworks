package org.example.app.exceptions;

public class FileIsMissingException extends Exception {
    private final String message;

    public FileIsMissingException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
