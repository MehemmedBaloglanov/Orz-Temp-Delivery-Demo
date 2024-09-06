package com.intelliacademy.orizonroute.bundle;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intelliacademy.orizonroute.GObjects;

import java.util.*;

/*
 {
    "s": {
        "az": {
            "S-0000000001": "Success Lorem Ipsum",
        }
    },
    "f": {
        "az": {
            "F-0000000001": "Fail Lorem Ipsum",
        }
    }
 }
 */
public class InMemoryMessageBundle implements AbstractMessageBundle , InMemoryInitializer{

    private final Map<String,Map<String,Map<String,String>>> MESSAGE_POOL = new HashMap<>();

    {
        MESSAGE_POOL.put("success", new HashMap<>());
        MESSAGE_POOL.put("fail", new HashMap<>());
        Arrays.stream(Lang.values()).forEach(lang -> {
            MESSAGE_POOL.get("success").put(lang.lower(), new HashMap<>());
            MESSAGE_POOL.get("fail").put(lang.lower(), new HashMap<>());
        });
    }

    /**
     * @param lang - Language
     * @param keyExp - Key of message . For example: F-ORDER-0000000001
     * @param params - Parameters of message if needed
     * @return
     */
    @Override
    public String message(Lang lang, String keyExp, List<String> params) {
        var key = new Key(keyExp, lang);
        return MESSAGE_POOL.get(key.status).get(key.lang).get(keyExp);
    }

    @Override
    public void initialize(JsonNode node) {
        this.initialize(node,new ObjectMapper());
    }

    @Override
    @SuppressWarnings("all")
    public void initialize(JsonNode node, ObjectMapper objectMapper){
        var map = objectMapper.convertValue(node, Map.class);
        final String status = (String) map.get("status");
        final String language = (String) map.get("language");
        var prefixOfStatus = status.substring(0,1).toUpperCase(Locale.ROOT);

        var targetPool = MESSAGE_POOL
                .get(status)
                .get(language);
        ArrayList<LinkedHashMap<String,String>> contents = (ArrayList<LinkedHashMap<String, String>>) map.get("contents");
        contents.stream().map(xmap->{
            var key  = xmap.get("k");
            var value  = xmap.get("v");
            return new Pair(prefixOfStatus.concat("-").concat(key),value);
        }).forEach(pair->{
            targetPool.put(pair.key, pair.value);
        });
    }

    public record Pair(String key,String value){}

    public class Key {
        private String status;
        private String lang;
        public Key(String keyExp, Lang lang) {
            keyExp = GObjects.getOrThrow(keyExp).toLowerCase(Locale.ROOT);
            this.status = keyExp
                    .substring(0, 1)
                    .equalsIgnoreCase("s") ? "success" : "fail";
            this.lang = lang.name();
        }

    }

}
