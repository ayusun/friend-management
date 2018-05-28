package com.ayush.FriendManagement.enums;

public enum ErrorEnums {
    FRIEND_ALREADY_EXIST(1000, "Friend Already Exists"),
    DUPLICATE_FRIEND_NAME_INPUT(1001, "Duplicate Names For Friend"),
    SUBSCRIPTION_ALREADY_EXIST(2000, "Subscription Already Exist"),
    DUPLICATE_SUBSCRIPTION_NAME_INPUT(2001, "Duplicate Names For Subscription")
    ;

    private final int errorCode;
    private final String errorMessage;

    ErrorEnums(int code, String message){
        this.errorCode = code;
        this.errorMessage = message;
    }

    public int getErrorCode(){
        return this.errorCode;
    }

    public String getErrorMessage(){
        return this.errorMessage;
    }
}
