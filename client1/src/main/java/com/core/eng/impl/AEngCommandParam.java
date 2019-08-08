package com.core.eng.impl;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

abstract class AEngCommandParam {
    public abstract Object getRef();

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private String[] values;

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private Object data;
}
