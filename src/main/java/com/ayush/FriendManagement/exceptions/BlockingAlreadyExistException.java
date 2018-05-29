package com.ayush.FriendManagement.exceptions;

public class BlockingAlreadyExistException extends RuntimeException {
    public BlockingAlreadyExistException(){
        super("Blocking Record Already Exist Exception");
    }
}
