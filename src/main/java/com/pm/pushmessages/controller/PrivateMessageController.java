package com.pm.pushmessages.controller;

import com.pm.pushmessages.dto.Message;
import com.pm.pushmessages.dto.ResponseMessage;
import com.pm.pushmessages.service.MService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.HtmlUtils;

import java.security.Principal;

@Controller
public class PrivateMessageController {

    @Autowired
    private MService service;


}
