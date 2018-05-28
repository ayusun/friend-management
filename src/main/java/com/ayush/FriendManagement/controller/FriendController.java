package com.ayush.FriendManagement.controller;

import com.ayush.FriendManagement.RepeatedFriendNameException;
import com.ayush.FriendManagement.enums.ErrorEnums;
import com.ayush.FriendManagement.exceptions.FriendAlreadyExistException;
import com.ayush.FriendManagement.requestVO.FriendsVO;
import com.ayush.FriendManagement.requestVO.GetFriendsRequestVO;
import com.ayush.FriendManagement.service.FriendService;
import com.ayush.FriendManagement.vo.ErrorResponseVo;
import com.ayush.FriendManagement.vo.GetFriendsResponse;
import com.ayush.FriendManagement.vo.SucessReponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FriendController {

    @Autowired
    private FriendService friendService;

    @PutMapping(value = "/friends")
    public ResponseEntity<SucessReponseVO> createFriendShip(@RequestBody final FriendsVO friend){
        try{
            boolean response = friendService.createFriendShip(friend.getFriends());
        } catch(DataIntegrityViolationException e){
            throw new FriendAlreadyExistException();
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED).
                body(SucessReponseVO.createSucessResponseVo());
    }

    @PostMapping(value = "/friends")
    public ResponseEntity<GetFriendsResponse> getFriends(@RequestBody final GetFriendsRequestVO requestVo){
        List<String> friends = friendService.getFriends(requestVo.getEmail());
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(GetFriendsResponse.createFriendResponse(friends));
    }

    @ExceptionHandler(RepeatedFriendNameException.class)
    public ErrorResponseVo handleRepeatedFriendNameException(){
        return ErrorResponseVo.createErrorResponse(ErrorEnums.DUPLICATE_FRIEND_NAME_INPUT);
    }

    @ExceptionHandler(FriendAlreadyExistException.class)
    public ErrorResponseVo handleDuplicateFriendException(){
        return ErrorResponseVo.createErrorResponse(ErrorEnums.FRIEND_ALREADY_EXIST);
    }
}
