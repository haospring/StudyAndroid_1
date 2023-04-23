package com.hcs.testeventbus.event;

public class MessageUpdateText {
    private String message;

    public MessageUpdateText(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
