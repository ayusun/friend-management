package com.ayush.FriendManagement.vo;

import lombok.Data;

@Data
public class SucessReponseVO {
    private boolean success;

    public static SucessReponseVO createSucessResponseVo(){
        SucessReponseVO vo = new SucessReponseVO();
        vo.setSuccess(true);
        return vo;
    }
}
