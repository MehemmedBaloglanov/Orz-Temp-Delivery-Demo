package com.intelliacademy.orizonroute.repsonse;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.intelliacademy.orizonroute.JacksonTypeReference;

import java.time.OffsetDateTime;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response implements JacksonTypeReference {
    private UUID commandTrackingId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private final OffsetDateTime timestamp;
    private Boolean success;

    public Response() {
        this.timestamp = OffsetDateTime.now();
    }

    public Response(Boolean success) {
        this.success = success;
        this.timestamp = OffsetDateTime.now();
    }
    public Response(UUID commandTrackingId,Boolean success) {
        this.commandTrackingId = commandTrackingId;
        this.success = success;
        this.timestamp = OffsetDateTime.now();
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String toString() {
        return "Response [success=" + success + "]";
    }

    public Boolean isSuccess() {
        return success;
    }

    public OffsetDateTime getTimestamp() {
        return timestamp;
    }

    public UUID getCommandTrackingId() {
        return commandTrackingId;
    }

    @JsonIgnore
    public Boolean isFailure() {
        return !this.isSuccess();
    }

}
