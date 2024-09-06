package com.intelliacademy.orizonroute.repsonse;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.intelliacademy.orizonroute.ListUtil;

import java.util.List;


@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class Message {
    public abstract Boolean getIsSingle();

    public enum Label {
        NONE,
        WARNING,
        INFO,
        ERROR,
        CRITICAL;
    }

    public static class Multiple extends Message {
        private List<Single> messages;

        public Multiple(List<Single> messages) {
            this.messages = messages;
        }

        public static Multiple of(List<Single> messages) {
            return new Multiple(messages);
        }

        public List<Single> getMessages() {
            return messages;
        }

        @Override
        public Boolean getIsSingle() {
            return false;
        }
    }


    public static class Single extends Message{
        private Boolean isCode;
        private Label label;
        private String context;
        private List<String> params;

        public Single(Label label, Boolean isCode, String context, List<String> params) {
            this.label = label;
            this.isCode = isCode;
            this.context = context;
            this.params = params;
        }

        public Single(String messageKey, List<String> params) {
            this(Label.NONE, true, messageKey, params);
        }

        public Single(Boolean isCode, String messageKey, List<String> params) {
            this(Label.NONE, isCode, messageKey, params);
        }

        public Single(String messageKey) {
            this(Label.NONE, true, messageKey, null);
        }

        public Single(Boolean isCode, String context) {
            this(Label.NONE, isCode, context, null);
        }


        public List<String> getParams() {
            return params;
        }

        public Boolean getIsCode() {
            return isCode;
        }

        public Label getLabel() {
            return label;
        }

        public String getContext() {
            return context;
        }

        @Override
        public Boolean getIsSingle() {
            return true;
        }

        public static Message parameterlessKeyMessage(String messageKey){
            return new Single(messageKey);
        }

        public static Message parameterlessRawMessage(String rawMessage){
            return new Single(false, rawMessage);
        }

        public static Message parameterizedKeyMessage(String messageKey, List<String> params){
            if (ListUtil.isNotEmpty(params)) return new Single(messageKey, params);
            return parameterlessKeyMessage(messageKey);
        }

        public static Message parameterizedRawMessage(String rawMessage, List<String> params){
            return new Single(false, rawMessage, ListUtil.requireNonEmpty(params));
        }

    }

}
