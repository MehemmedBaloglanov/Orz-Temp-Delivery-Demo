package com.intelliacademy.orizonroute.root;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.experimental.SuperBuilder;

import java.util.Objects;
import java.util.UUID;

@SuperBuilder
public class BaseRoot<ID extends BaseID<UUID>> {
    private ID id;

    public BaseRoot() {}

    public BaseRoot(ID value) {
        this.id = value;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseRoot<?> baseId = (BaseRoot<?>) o;
        return id.equals(baseId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @JsonIgnore
    public Boolean isEmpty(){
        return this.id == null;
    }

    public void setId(ID id) {
        this.id = id;
    }

    @JsonIgnore
    public ID getRootID() {
        return this.id;
    }

    @JsonIgnore
    public UUID rawId(){
        return this.id.value();
    }
}
