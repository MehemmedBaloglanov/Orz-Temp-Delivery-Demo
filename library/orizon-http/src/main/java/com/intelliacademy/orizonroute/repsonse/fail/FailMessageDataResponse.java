package com.intelliacademy.orizonroute.repsonse.fail;

import com.intelliacademy.orizonroute.repsonse.MessageDataResponse;
import com.intelliacademy.orizonroute.repsonse.ResponseData;

public class FailMessageDataResponse<D extends ResponseData> extends MessageDataResponse<D> {
    protected FailMessageDataResponse(String message, D data) {
        super(Boolean.FALSE, message, data);
    }

    public static <D  extends ResponseData> FailMessageDataResponse<D> of(String message, D data){
        return new FailMessageDataResponse<>(message, data);
    }

    public static FailMessageDataResponse<ResponseData.Nil> of(String message){
        return new FailMessageDataResponse<>(message, ResponseData.Nil.INSTANCE);
    }
}
