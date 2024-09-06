package com.intelliacademy.orizonroute.repsonse.success;

import com.intelliacademy.orizonroute.repsonse.Message;
import com.intelliacademy.orizonroute.repsonse.MessageDataResponse;
import com.intelliacademy.orizonroute.repsonse.ResponseData;

import java.util.Objects;

public class SuccessMessageDataResponse <D  extends ResponseData> extends MessageDataResponse<D> {
    protected SuccessMessageDataResponse(String messageKey, D data) {
        super(Boolean.TRUE, messageKey, data);
    }

    protected SuccessMessageDataResponse(Message message, D data) {
        super(Boolean.TRUE, message, data);
    }

    public static <D  extends ResponseData> SuccessMessageDataResponse<D> of(String messageKey, D data){
        return new SuccessMessageDataResponse<>(messageKey, Objects.requireNonNull(data));
    }

    public static <D  extends ResponseData> SuccessMessageDataResponse<D> of(Message message, D data){
        return new SuccessMessageDataResponse<>(message, Objects.requireNonNull(data));
    }


}
