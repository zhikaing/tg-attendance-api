package com.tg.attendance.module;

import com.google.inject.AbstractModule;
import com.tg.attendance.context.MfbDSL;
import com.tg.attendance.context.MfbDSLContext;
import com.tg.attendance.service.BanyuanPersistanceService;
import com.tg.attendance.service.BanyuanPersistanceServiceImpl;

public class BanyuanModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(BanyuanPersistanceService.class).to(BanyuanPersistanceServiceImpl.class);
        bind(MfbDSL.class).to(MfbDSLContext.class);
    }
}