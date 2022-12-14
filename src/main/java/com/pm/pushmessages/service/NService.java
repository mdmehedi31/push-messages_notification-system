package com.pm.pushmessages.service;

import com.pm.pushmessages.dto.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class NService {

    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public NService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendGlobalNotification(){
        ResponseMessage message= new ResponseMessage("Global Notification");
        messagingTemplate.convertAndSend("/topic/global-notifications", message);
    }

    public void sendPrivateNotification(final String userId){
        ResponseMessage message= new ResponseMessage("Private Notification");
        messagingTemplate.convertAndSendToUser(userId,"/topic/private-notifications", message);
    }
}
