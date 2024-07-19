package com.app.balance.model.exception;


public class UserAlreadyExistException extends RuntimeException{
    public UserAlreadyExistException(String message){
        super (message);
    }
}
