package com.ayush.FriendManagement.vo;

import com.ayush.FriendManagement.enums.ErrorEnums;
import lombok.Data;

@Data
public class ErrorResponseVo {
    private int errorCode;

    private String errorMessage;

    private boolean success = false;

    public static ErrorResponseVo createErrorResponse(ErrorEnums errorEnum){
        ErrorResponseVo vo = new ErrorResponseVo();
        vo.setErrorCode(errorEnum.getErrorCode());
        vo.setErrorMessage(errorEnum.getErrorMessage());
        vo.setSuccess(false);
        return vo;
    }
}
