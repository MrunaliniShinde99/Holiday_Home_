package com.holiday.home.exceptions;


public class AuthException extends RuntimeException{
    public AuthException() {
        super("Authentication Issue.");
    }
}
