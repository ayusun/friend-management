package com.ayush.FriendManagement.vo;

import lombok.Builder;
import lombok.Data;

@Data
public class ErrorResponseVo {
    private int errorCode;

    private String errorMessage;
}
