package com.pm.pushmessages.dto;

import lombok.Data;

@Data
public class ResponseMessage {

    private String responseMessage;

    public ResponseMessage(){

    }


    public ResponseMessage(String content){
        this.responseMessage=content;
    }
}
