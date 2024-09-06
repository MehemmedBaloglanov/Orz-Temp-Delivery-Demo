package com.intelliacademy.orizonroute.adapters;

import com.intelliacademy.orizonroute.root.AggregateRoot;
import com.intelliacademy.orizonroute.valueobjects.common.RowStatus;

public interface AbstractCommandRepositoryAdapter<R extends AggregateRoot<?>>{
    R merge(R r);

    default R delete(R r){
        r.setRowStatus(RowStatus.DELETED_BY_USER);
        return this.merge(r);
    }
}
