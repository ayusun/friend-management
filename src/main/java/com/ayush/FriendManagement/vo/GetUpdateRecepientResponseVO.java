package com.ayush.FriendManagement.vo;

import lombok.Data;

import java.util.List;

@Data
public class GetUpdateRecepientResponseVO {

    private boolean success;

    private List<String> recepients;

    public static GetUpdateRecepientResponseVO createSuccessResponse(List<String> recepients){
        GetUpdateRecepientResponseVO vo = new GetUpdateRecepientResponseVO();
        vo.setSuccess(true);
        vo.setRecepients(recepients);
        return vo;
    }
}
