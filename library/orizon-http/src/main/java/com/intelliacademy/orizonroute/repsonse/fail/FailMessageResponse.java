package com.intelliacademy.orizonroute.repsonse.fail;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.intelliacademy.orizonroute.repsonse.Message;
import com.intelliacademy.orizonroute.repsonse.MessageResponse;
import com.intelliacademy.orizonroute.repsonse.Response;

import java.util.List;
import java.util.Map;


public class FailMessageResponse extends MessageResponse {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String,String> badRequestFields;
    public FailMessageResponse() {
        super(false, "F-0000000001");
    }

    public FailMessageResponse(String messageKey) {
        super(false, messageKey);
    }

    public FailMessageResponse(String messageKey, Map<String,String> badRequestFields) {
        super(false, messageKey);
        this.badRequestFields = badRequestFields;
    }
    public FailMessageResponse(Message message) {
        super(false, message);
    }

    public Map<String,String> getBadRequestFields() {
        return badRequestFields;
    }

    public static FailMessageResponse of(String messageKey) {
        return new FailMessageResponse(messageKey);
    }
}
