package com.intelliacademy.orizonroute.valueobjects.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.intelliacademy.orizonroute.valueobjects.user.PhoneNumberType;

/**
 * Raw Phone number must be x-xxxxxxxxx
 */
@ValueObject
public sealed class PhoneNumber permits PhoneNumber.Nil{
    public static final String WITHOUT_COUNTRY_CODE_PHONE_NUMBER_EXP = "^[0-9]{8,15}$";
    public static final String COUNTRY_CODE_EXP = "^[0-9]{1,3}$";
    private final PhoneNumberType type;
    private final String countryCode;
    private final String number;

    private PhoneNumber(PhoneNumberType type,String countryCode, String number) {
        this.type = type;
        this.number = number;
        this.countryCode = countryCode;
    }

    public PhoneNumber validate(){
        if (!this.isValid())
            throw new IllegalArgumentException("Invalid phone number");
        return this;
    }

    public static PhoneNumber nil() {
        return new Nil();
    }

    public static PhoneNumber ofNullable(PhoneNumberType type,String countryCode, String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isEmpty() || type == null || countryCode == null || countryCode.isEmpty())
            return PhoneNumber.nil();
        return new PhoneNumber(type, countryCode,phoneNumber);
    }

    public static PhoneNumber of(PhoneNumberType type, String countryCode,String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isEmpty())
            throw new IllegalArgumentException("Email cannot be null or empty");
        return new PhoneNumber(type,countryCode, phoneNumber);
    }

    public static PhoneNumber of(PhoneNumberType type, String rawPhoneNumber) {
        if (rawPhoneNumber == null || rawPhoneNumber.isEmpty())
            throw new IllegalArgumentException("Email cannot be null or empty");
        if (!rawPhoneNumber.contains("-"))
            throw new IllegalArgumentException("Invalid phone number format, must be x-xxxxxxxxx");
        var values = rawPhoneNumber.split("-");
        return new PhoneNumber(type,values[0], values[1]);
    }

    @JsonIgnore
    public Boolean isValid() {
        return !this.isNil() &&
                this.number.matches(WITHOUT_COUNTRY_CODE_PHONE_NUMBER_EXP)
                && this.countryCode.matches(COUNTRY_CODE_EXP);
    }

    @JsonIgnore
    public Boolean isNil() {
        return false;
    }

    public PhoneNumberType getType() {
        return type;
    }

    public String getNumber() {
        return number;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public static final class Nil extends PhoneNumber {
        private Nil() {
            super(PhoneNumberType.NONE, "","");
        }

        @Override
        public Boolean isNil() {
            return true;
        }

        @Override
        public Boolean isValid() {
            return false;
        }

        @Override
        public PhoneNumberType getType() {
            throw new UnsupportedOperationException("Nil object does not have a type");
        }

        @Override
        public String getCountryCode() {
            throw new UnsupportedOperationException("Nil object does not have a country code");
        }

        @Override
        public String getNumber() {
            throw new UnsupportedOperationException("Nil object does not have a value");
        }
    }
}
