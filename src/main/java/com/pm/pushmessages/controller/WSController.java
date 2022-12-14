package com.pm.pushmessages.controller;


import com.pm.pushmessages.dto.Message;
import com.pm.pushmessages.service.MService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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

    @PostMapping("/send-private-message/{id}")
    public void sendPrivateMessage(@PathVariable final String id,
                                   @RequestBody final Message message){
        mService.notifyUser(id, message.getMessageContent());
    }
}
