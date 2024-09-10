package com.intellibucket.company.service.domain.core.valueobject;

import com.intelliacademy.orizonroute.identity.company.CompanyID;
import com.intelliacademy.orizonroute.valueobjects.common.Money;
import com.intelliacademy.orizonroute.valueobjects.common.ValueObject;

@ValueObject
public class CompanyAddress {
    private String city;
    private String street;
    private String address;
}
