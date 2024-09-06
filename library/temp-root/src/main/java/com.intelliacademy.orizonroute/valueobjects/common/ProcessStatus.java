package com.intelliacademy.orizonroute.valueobjects.common;
@ValueObject
public enum ProcessStatus {
    ON_PROGRESS, FAILED, COMPLETED, ROLL_BACKED;

    public Boolean isOnProgress(){
        return this.equals(ON_PROGRESS);
    }

    public Boolean isFailed(){
        return this.equals(FAILED);
    }

    public Boolean isCompleted(){
        return this.equals(COMPLETED);
    }

    public Boolean isRollBacked(){
        return this.equals(ROLL_BACKED);
    }
}
