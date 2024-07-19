package com.app.balance.model.exception;

public class UserNotExistException extends RuntimeException{
    public UserNotExistException(String message){
        super(message);
    }
}
