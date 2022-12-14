package com.pm.pushmessages.service;

import com.pm.pushmessages.dto.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class MService {

    private final SimpMessagingTemplate messagingTemplate;
    private final NService nService;
    @Autowired
    public MService(SimpMessagingTemplate messagingTemplate, NService nService) {
        this.messagingTemplate = messagingTemplate;
        this.nService = nService;
    }
    public void notifyFrontend(final String message){
        ResponseMessage response= new ResponseMessage(message);
        nService.sendGlobalNotification();
        messagingTemplate.convertAndSend("/topic/messages", response);
    }
    public void notifyUser(final String id, final String message){

        ResponseMessage response= new ResponseMessage(message);
        nService.sendPrivateNotification(id);
        messagingTemplate.convertAndSendToUser(id,
                "/topic/private-messages", response);
    }

}
