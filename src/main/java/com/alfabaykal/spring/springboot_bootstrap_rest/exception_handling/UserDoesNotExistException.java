package com.alfabaykal.spring.springboot_bootstrap_rest.exception_handling;

public class UserDoesNotExistException extends RuntimeException{

    public UserDoesNotExistException(String message) {
        super(message);
    }
}
