package com.pm.pushmessages.controller;

import com.pm.pushmessages.dto.Message;
import com.pm.pushmessages.dto.ResponseMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import java.security.Principal;

@Controller
public class PrivateMessageController {

    @MessageMapping("/private-message")
    @SendToUser("/topic/private-messages")
    public ResponseMessage getPrivateMessage(final Message message, final Principal principal) throws InterruptedException {
        Thread.sleep(1000);

        return new ResponseMessage(HtmlUtils.htmlEscape("Sending private message to user: "+ principal.getName()+
                ": "+ message.getMessageContent()));
    }
}
