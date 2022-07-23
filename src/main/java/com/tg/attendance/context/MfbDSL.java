package com.tg.attendance.context;

import org.jooq.impl.DefaultDSLContext;

public interface MfbDSL {
    DefaultDSLContext getContext() throws Exception;
}
