package com.ayush.FriendManagement;

public class RepeatedFriendNameException extends RuntimeException {

    public RepeatedFriendNameException(String friend1, String friend2){
        super(friend1 + " and " + friend2 + " are same.");
    }

}
