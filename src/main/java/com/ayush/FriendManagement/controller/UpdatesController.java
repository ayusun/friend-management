package com.ayush.FriendManagement.controller;


import com.ayush.FriendManagement.requestVO.UpdateRequestVO;

import com.ayush.FriendManagement.vo.SucessReponseVO;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UpdatesController {


    @PutMapping(value = "/updates")
    public ResponseEntity<SucessReponseVO> createBlocking(@RequestBody final UpdateRequestVO request){

    }

}
