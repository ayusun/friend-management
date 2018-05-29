package com.ayush.FriendManagement.exceptions;

public class FriendshipBlockedException extends RuntimeException {
    public FriendshipBlockedException(){
        super("Friendship is blocked");
    }
    public FriendshipBlockedException(String email1, String email2){
        super("Friendship is blocked between " + email1 + " and " + email2);
    }
}
