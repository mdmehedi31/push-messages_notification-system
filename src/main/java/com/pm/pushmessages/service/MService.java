package com.pm.pushmessages.service;

import com.pm.pushmessages.dto.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class MService {

    private final SimpMessagingTemplate messagingTemplate;


    @Autowired
    public MService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void notifyFrontend(final String message){
        ResponseMessage response= new ResponseMessage(message);
        messagingTemplate.convertAndSend("/topic/messages", response);
    }
}
