package com.pm.pushmessages.controller;

import com.pm.pushmessages.dto.Message;
import com.pm.pushmessages.dto.ResponseMessage;
import com.pm.pushmessages.service.NService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import java.security.Principal;

@Controller
public class MessageController {


    @Autowired
    private NService nService;
    @MessageMapping("/message")
    @SendTo("/topic/messages")
    public ResponseMessage getMessage(final Message message) throws InterruptedException {
        Thread.sleep(1000);
        nService.sendGlobalNotification();
        return new ResponseMessage(HtmlUtils.htmlEscape(message.getMessageContent()));
    }

    @MessageMapping("/private-message")
    @SendToUser("/topic/private-messages")
    public ResponseMessage getPrivateMessage(final Message message, final Principal principal)
            throws InterruptedException {

        Thread.sleep(1000);
        nService.sendPrivateNotification(principal.getName());
        return new ResponseMessage(HtmlUtils.htmlEscape("Sending private message to user: "+
                principal.getName()+
                ": "+ message.getMessageContent()));
    }

}
