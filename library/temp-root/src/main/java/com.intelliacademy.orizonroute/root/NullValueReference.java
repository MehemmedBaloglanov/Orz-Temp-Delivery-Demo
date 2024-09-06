package com.intelliacademy.orizonroute.root;

public interface NullValueReference<R> {
    default Boolean isNil(){
        return false;
    }
}
