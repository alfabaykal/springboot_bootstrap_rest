package com.alfabaykal.spring.springboot_bootstrap_rest.exception_handling;

public class UserDoesNotExist {

    private String info;

    public UserDoesNotExist() {
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
