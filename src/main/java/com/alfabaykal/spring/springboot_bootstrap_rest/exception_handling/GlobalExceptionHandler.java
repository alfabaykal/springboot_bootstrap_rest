package com.alfabaykal.spring.springboot_bootstrap_rest.exception_handling;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<RoleDoesNotExist> handleException (RoleDoesNotExistException e) {
        RoleDoesNotExist data = new RoleDoesNotExist();
        data.setInfo(e.getMessage());

        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<UserDoesNotExist> handleException (UserDoesNotExistException e) {
        UserDoesNotExist data = new UserDoesNotExist();
        data.setInfo(e.getMessage());

        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<UserDoesNotExist> handleException (Exception e) {
        UserDoesNotExist data = new UserDoesNotExist();
        data.setInfo(e.getMessage());

        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }

}
