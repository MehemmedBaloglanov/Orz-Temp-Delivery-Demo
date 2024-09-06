package com.intelliacademy.orizonroute.valueobjects.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.intelliacademy.orizonroute.valueobjects.user.EmailType;

import java.util.Locale;

@ValueObject
public sealed class Email permits Email.Nil{
    public static  final String EXP = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
    private final EmailType type;
    private final String value;

    private Email(EmailType type, String value) {
        this.type = type;
        this.value = value;
    }

    public static Email nil() {
        return new Nil();
    }

    public static Email ofNullable(EmailType type, String email) {
        if (email == null || email.isEmpty())
            return Email.nil();
        return new Email(type, email.toLowerCase(Locale.ROOT));
    }

    public static Email of(EmailType type, String email) {
        if (email == null || email.isEmpty())
            throw new IllegalArgumentException("Email cannot be null or empty");
        if (!email.matches(EXP))
            throw new IllegalArgumentException("Invalid email format");
        return new Email(type, email.trim().toLowerCase(Locale.ROOT));
    }

    @JsonIgnore
    public Boolean isValid() {
        return !this.isNil() && this.value.matches(EXP);
    }

    @JsonIgnore
    public Boolean isNil() {
        return false;
    }

    public EmailType getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public static final class Nil extends Email {
        private Nil() {
            super(EmailType.NONE, "");
        }

        @Override
        public Boolean isNil() {
            return true;
        }

        @Override
        public Boolean isValid() {
            return false;
        }
    }
}
