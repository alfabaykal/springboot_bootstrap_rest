package com.alfabaykal.spring.springboot_bootstrap.exception_handling;

import org.springframework.dao.DataAccessException;

public class RoleDoesNotExistException extends DataAccessException {

    public RoleDoesNotExistException(String message) {
        super(message);
    }

}
