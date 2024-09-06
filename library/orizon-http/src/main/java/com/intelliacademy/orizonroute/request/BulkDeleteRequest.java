package com.intelliacademy.orizonroute.request;


import java.util.List;
import java.util.UUID;

public record BulkDeleteRequest(List<UUID> targetIds){ }
