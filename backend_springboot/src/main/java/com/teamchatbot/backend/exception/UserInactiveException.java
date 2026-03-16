package com.teamchatbot.backend.exception;

public class UserInactiveException extends RuntimeException {

    public UserInactiveException(String message) {
        super(message);
    }

}

