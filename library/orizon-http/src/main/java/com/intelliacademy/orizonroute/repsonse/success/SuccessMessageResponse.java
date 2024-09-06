package com.intelliacademy.orizonroute.repsonse.success;

import com.intelliacademy.orizonroute.repsonse.MessageResponse;
import com.intelliacademy.orizonroute.repsonse.Response;

import java.util.List;
import java.util.UUID;

public class SuccessMessageResponse extends MessageResponse {
    public SuccessMessageResponse() {
        super(true, "S-0000000001");
    }

    public SuccessMessageResponse(String message) {
        super(true, message);
    }
    public SuccessMessageResponse(String message, List<String> params) {
        super(true, message,params);
    }

    public SuccessMessageResponse(UUID trackingId, String message) {
        super(trackingId,true, message);
    }

    public static SuccessMessageResponse of(String message, List<String> params) {
        return new SuccessMessageResponse(message,params);
    }

    public static SuccessMessageResponse of(UUID trackingId, String message) {
        return new SuccessMessageResponse(trackingId,message);
    }
}
