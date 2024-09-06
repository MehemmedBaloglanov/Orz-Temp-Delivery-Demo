package com.intelliacademy.orizonroute.bundle;

import java.util.List;

public interface AbstractMessageBundle {
    default String message(String key){
        return this.message(Lang.AZ, key);
    }

    default String message(Lang lang, String key){
        return this.message(lang, key, List.of());
    }

    default String message(String key, List<String> params){
        return this.message(Lang.AZ, key, params);
    }

    String message(Lang lang, String key, List<String> params);
}
