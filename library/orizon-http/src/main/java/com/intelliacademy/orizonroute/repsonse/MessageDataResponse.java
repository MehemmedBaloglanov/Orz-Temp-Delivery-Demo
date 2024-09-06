package com.intelliacademy.orizonroute.repsonse;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.intelliacademy.orizonroute.repsonse.fail.FailMessageDataResponse;
import com.intelliacademy.orizonroute.repsonse.success.SuccessMessageDataResponse;

import java.util.UUID;

public class MessageDataResponse<D extends ResponseData> extends MessageResponse{
    private D data;

    protected MessageDataResponse(Boolean success, String messageKey, D data) {
        super(UUID.randomUUID() ,success, messageKey);
        this.data = data;
    }

    protected MessageDataResponse(Boolean success, Message message, D data) {
        super(UUID.randomUUID(),success, message);
        this.data = data;
    }

    public D getData() {
        return data;
    }

    @JsonIgnore
    public Boolean hasData() {
        return data != null;
    }

    public void setData(D data) {
        this.data = data;
    }

}
