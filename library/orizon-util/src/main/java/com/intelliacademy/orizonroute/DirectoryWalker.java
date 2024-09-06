package com.intelliacademy.orizonroute;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DirectoryWalker {
    // pattern **/s_*.json or **/f_*.json
    public Set<JsonNode> walk(Path directoryPath, ObjectMapper objectMapper) throws IOException {
        Predicate<Path> filecPredicate =
                (Path path) -> path.toString().matches(".*[s|f]_.*\\.json")
                        && Files.isRegularFile(path)
                        && Files.isReadable(path);
        return Files.walk(directoryPath)
                .filter(filecPredicate)
                .unordered()
                .map(path -> {
                    try {
                        String content = new String(Files.readAllBytes(path));
                        return objectMapper.readTree(content);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return null;
                    }
                }).collect(Collectors.toSet());
    }
}
