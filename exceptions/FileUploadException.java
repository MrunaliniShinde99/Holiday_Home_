package com.holiday.home.exceptions;

public class FileUploadException extends RuntimeException{
    public FileUploadException() {
        super("File upload issue.");
    }

    public FileUploadException(String message) {
        super(message);
    }
}
