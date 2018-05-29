package com.ayush.FriendManagement.controller;


import com.ayush.FriendManagement.requestVO.UpdateRequestVO;

import com.ayush.FriendManagement.service.UpdateService;
import com.ayush.FriendManagement.vo.GetUpdateRecepientResponseVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UpdatesController {

    @Autowired
    private UpdateService updateService;


    @PutMapping(value = "/updates")
    public ResponseEntity<GetUpdateRecepientResponseVO> createBlocking(@RequestBody final UpdateRequestVO request){
        List<String> recepients = updateService.getRecipients(request.getSender(), request.getText());

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(GetUpdateRecepientResponseVO.createSuccessResponse(recepients));
    }

}
