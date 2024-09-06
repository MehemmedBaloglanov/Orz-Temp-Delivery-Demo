package com.intelliacademy.orizonroute.repsonse;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.UUID;

public class ResponseData {
    public static ResponseData of() {
        return new CommandResponseData();
    }

    public static class Nil extends ResponseData {
        public static final Nil INSTANCE = new Nil();
    }
}
