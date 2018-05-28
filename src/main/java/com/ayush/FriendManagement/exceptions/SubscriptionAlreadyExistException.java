package com.ayush.FriendManagement.exceptions;

public class SubscriptionAlreadyExistException extends RuntimeException {
    public SubscriptionAlreadyExistException(){
        super("Subscription Already Exist Exception");
    }
}
