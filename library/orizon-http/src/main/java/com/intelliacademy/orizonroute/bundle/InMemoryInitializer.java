package com.intelliacademy.orizonroute.bundle;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public interface InMemoryInitializer {
    void initialize(JsonNode node);
    void initialize(JsonNode node, ObjectMapper objectMapper);
}
