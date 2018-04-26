package com.start.boot.domain;

/**
 * Created by lenovo on 2017/10/24.
 */
public class CustomerException extends  RuntimeException{

    public CustomerException(String message) {
        super(message);
    }

    public CustomerException(String message, Throwable cause) {
        super(message, cause);
    }
}
