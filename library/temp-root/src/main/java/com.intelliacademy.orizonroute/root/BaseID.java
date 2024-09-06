package com.intelliacademy.orizonroute.root;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.intelliacademy.orizonroute.util.UUIDv7;
import lombok.experimental.SuperBuilder;

import java.util.Objects;
import java.util.UUID;

@SuperBuilder
public class BaseID<ID> {
    private ID uuid;

    public BaseID() {}

    public BaseID(ID value) {
        this.uuid = value;
    }

    public static <ID> BaseID<ID> of(ID value){
        return new BaseID<>(value);
    }

    public static BaseID<UUID> of(){
        return new BaseID<>(UUIDv7.randomUUID());
    }

    @Override
    @JsonIgnore
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseID<?> baseId = (BaseID<?>) o;
        return uuid.equals(baseId.uuid);
    }

    @Override
    @JsonIgnore
    public int hashCode() {
        return Objects.hash(uuid);
    }

    @JsonIgnore
    public Boolean isNil(){
        return this.uuid == null;
    }

    @JsonIgnore
    public ID value() {
        return this.uuid;
    }

    @Override
    public String toString() {
        return uuid.toString();
    }
}
