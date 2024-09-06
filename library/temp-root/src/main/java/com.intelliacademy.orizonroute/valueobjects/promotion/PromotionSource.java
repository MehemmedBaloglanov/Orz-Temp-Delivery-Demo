package com.intelliacademy.orizonroute.valueobjects.promotion;

import com.intelliacademy.orizonroute.valueobjects.common.ValueObject;

@ValueObject
public enum PromotionSource {
    SYSTEM,
    USER,
    PARTNER,
    STORE,
    MARKETPLACE,
    PLAYGROUND,
    OTHER;
}
