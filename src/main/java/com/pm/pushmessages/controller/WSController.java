package com.pm.pushmessages.controller;


import com.pm.pushmessages.dto.Message;
import com.pm.pushmessages.service.MService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WSController {

    @Autowired
    private MService mService;

    @PostMapping("/send-message")
    public void sendMessage(@RequestBody final Message message){

        mService.notifyFrontend(message.getMessageContent());
    }

}
