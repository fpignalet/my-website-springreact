package com.core.eng.impl;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

public class EngCommandParam {
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private String[] values;

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private Object rec;
}
