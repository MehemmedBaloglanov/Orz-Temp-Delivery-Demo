package com.intelliacademy.orizonroute;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface JacksonTypeReference {
    @JsonIgnore
    default String getTypeReference(){
        return this.getClass().getSimpleName();
    }
}
