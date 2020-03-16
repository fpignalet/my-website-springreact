package com.core.data;

import com.core.data.impl.mq.MQTransaction;

public interface IMQRepository {
    void save(MQTransaction transaction);
}
