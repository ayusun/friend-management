package com.ayush.FriendManagement.exceptions;

public class FriendAlreadyExistException extends RuntimeException {
    public FriendAlreadyExistException(){
        super("Friend Already Exist Exception");
    }
}
