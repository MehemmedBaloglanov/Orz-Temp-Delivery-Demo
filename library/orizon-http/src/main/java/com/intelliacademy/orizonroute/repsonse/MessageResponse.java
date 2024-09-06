package com.intelliacademy.orizonroute.repsonse;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;
import java.util.UUID;

public class MessageResponse extends Response{
    private Message message;

    public MessageResponse() {}
    protected MessageResponse(Boolean success, String messageKey) {
        this(null,success, messageKey);
    }

    protected MessageResponse(Boolean success, String messageKey , List<String> params) {
        this(null,success, Message.Single.parameterizedKeyMessage(messageKey,params));
    }

    protected MessageResponse(Boolean success, Message message) {
        this(null,success, message);
    }

    protected MessageResponse(UUID trackingId,Boolean success, String messageKey) {
        this(trackingId,success, Message.Single.parameterlessKeyMessage(messageKey));
    }

    protected MessageResponse(UUID trackingId,Boolean success, Message message) {
        super(trackingId,success);
        this.message = message;
    }

    @JsonIgnore
    public Boolean hasMessage() {
        return message != null;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public String toString() {
        return "MessageResponse [message=" + message + "]";
    }

    public static class Prototype{
        public static final MessageResponse SUCCESS = new MessageResponse(true, "Success");
        public static final MessageResponse FAILURE = new MessageResponse(false, "Failure");

        public static MessageResponse success(String message) {
            return new MessageResponse(true, message);
        }

        public static MessageResponse failure(String message) {
            return new MessageResponse(false, message);
        }
    }
}
