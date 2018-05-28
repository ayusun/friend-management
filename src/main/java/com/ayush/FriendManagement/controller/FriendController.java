package com.ayush.FriendManagement.controller;

import com.ayush.FriendManagement.RepeatedFriendNameException;
import com.ayush.FriendManagement.enums.ErrorEnums;
import com.ayush.FriendManagement.exceptions.FriendAlreadyExistException;
import com.ayush.FriendManagement.requestVO.FriendsVO;
import com.ayush.FriendManagement.service.FriendService;
import com.ayush.FriendManagement.vo.ErrorResponseVo;
import com.ayush.FriendManagement.vo.SucessReponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class FriendController {

    @Autowired
    private FriendService friendService;

    @PostMapping(value = "/friends")
    public ResponseEntity<SucessReponseVO> createFriendShip(@RequestBody final FriendsVO friend){
        try{
            boolean response = friendService.createFriendShip(friend.getFriends());
        } catch(DataIntegrityViolationException e){
            throw new FriendAlreadyExistException();
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED).
                body(SucessReponseVO.createSucessResponseVo());
    }

    @ExceptionHandler(RepeatedFriendNameException.class)
    public ErrorResponseVo handleRepeatedFriendNameException(){
        ErrorResponseVo vo = new ErrorResponseVo();
        vo.setErrorCode(ErrorEnums.DUPLICATE_FRIEND_NAME_INPUT.getErrorCode());
        vo.setErrorMessage(ErrorEnums.DUPLICATE_FRIEND_NAME_INPUT.getErrorMessage());

        return vo;

    }

    @ExceptionHandler(FriendAlreadyExistException.class)
    public ErrorResponseVo handleDuplicateFriendException(){

        ErrorResponseVo vo = new ErrorResponseVo();
        vo.setErrorCode(ErrorEnums.FRIEND_ALREADY_EXIST.getErrorCode());
        vo.setErrorMessage(ErrorEnums.FRIEND_ALREADY_EXIST.getErrorMessage());

        return vo;

    }
}
