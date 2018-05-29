package com.ayush.FriendManagement.controller;

import com.ayush.FriendManagement.RepeatedArgumentException;
import com.ayush.FriendManagement.enums.ErrorEnums;
import com.ayush.FriendManagement.exceptions.SubscriptionAlreadyExistException;
import com.ayush.FriendManagement.requestVO.BlockingRequestVO;
import com.ayush.FriendManagement.requestVO.SubscriptionRequestVO;
import com.ayush.FriendManagement.service.BlockService;
import com.ayush.FriendManagement.service.SubscriptionService;
import com.ayush.FriendManagement.vo.ErrorResponseVo;
import com.ayush.FriendManagement.vo.SucessReponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BlockingController {

    @Autowired
    private BlockService blockService;

    @PutMapping(value = "/blocks")
    public ResponseEntity<SucessReponseVO> createBlocking(@RequestBody final BlockingRequestVO request){
        try{
            boolean response = blockService.createBlocking(request.getRequestor(), request.getTarget());
        } catch(DataIntegrityViolationException e){
            throw new SubscriptionAlreadyExistException();
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED).
                body(SucessReponseVO.createSucessResponseVo());
    }

    @ExceptionHandler(RepeatedArgumentException.class)
    public ErrorResponseVo handleRepeatedFriendNameException(){
        return ErrorResponseVo.createErrorResponse(ErrorEnums.DUPLICATE_BLOCKING_NAME_INPUT);
    }

    @ExceptionHandler(SubscriptionAlreadyExistException.class)
    public ErrorResponseVo handleDuplicateFriendException(){
        return ErrorResponseVo.createErrorResponse(ErrorEnums.BLOCKING_ALREADY_EXIST);
    }
}
