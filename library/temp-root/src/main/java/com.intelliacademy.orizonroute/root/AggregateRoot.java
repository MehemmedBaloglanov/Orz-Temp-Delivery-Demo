package com.intelliacademy.orizonroute.root;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.intelliacademy.orizonroute.valueobjects.common.ProcessStatus;
import com.intelliacademy.orizonroute.valueobjects.common.RowStatus;
import com.intelliacademy.orizonroute.valueobjects.common.Version;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
public abstract class AggregateRoot<ID extends BaseID<UUID>> extends BaseRoot<ID> implements NullValueReference<AggregateRoot<ID>> {

    @Builder.Default
    protected Version version = Version.START;
    @Builder.Default
    protected ProcessStatus processStatus = ProcessStatus.COMPLETED;
    @Builder.Default
    protected RowStatus rowStatus = RowStatus.ACTIVE;
    @Builder.Default
    protected OffsetDateTime createTs = OffsetDateTime.now();

    protected OffsetDateTime modificationTs;

    public AggregateRoot() {
        super();
    }

    @JsonIgnore
    public Boolean isNil() {
        return true;
    }

    protected AggregateRoot(ID value) {
        super(value);
    }

    public AggregateRoot(ID id,
                         Version version,
                         ProcessStatus processStatus,
                         RowStatus rowStatus,
                         OffsetDateTime createdDate,
                         OffsetDateTime modificationDate) {
        super(id);
        this.version = version;
        this.processStatus = processStatus;
        this.rowStatus = rowStatus;
        this.createTs = Objects.requireNonNullElse(createdDate, OffsetDateTime.now());
        this.modificationTs = modificationDate;
    }

    public AggregateRoot(ID id,
                         Long version,
                         ProcessStatus processStatus,
                         RowStatus rowStatus,
                         OffsetDateTime createdDate,
                         OffsetDateTime modificationDate) {
        super(id);
        this.version = new Version(version);
        this.processStatus = processStatus;
        this.rowStatus = rowStatus;
        this.createTs = Objects.requireNonNullElse(createdDate, OffsetDateTime.now());
        this.modificationTs = modificationDate;
    }

    public Long getVersionValue() {
        return this.version.value();
    }


    @JsonIgnore
    public Boolean sameIdentityAs(AggregateRoot<ID> other) {
        return Objects.equals(super.getRootID(), other.getRootID());
    }

    @JsonIgnore
    public Boolean sameVersionAs(AggregateRoot<ID> other) {
        return this.getVersionValue().equals(other.getVersionValue());
    }

    @JsonIgnore
    public Boolean sameVersionAs(Long otherVersion) {
        return this.getVersionValue().equals(otherVersion);
    }

    @JsonIgnore
    public Boolean isActive() {
        return this.rowStatus.isActive();
    }

    @JsonIgnore
    public  UUID getAbsoluteId() {
        return this.getRootID().value();
    }


    public Boolean isDeleted(){
        return this.rowStatus.isDeleted();
    }

    public <R extends AggregateRoot<ID>> R deleteAsUser() {
        this.rowStatus = RowStatus.DELETED_BY_USER;
        return (R) this;
    }



}
