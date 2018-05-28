package com.ayush.FriendManagement;

public class RepeatedArgumentException extends RuntimeException {

    public RepeatedArgumentException(String friend1, String friend2){
        super(friend1 + " and " + friend2 + " are same.");
    }

}
