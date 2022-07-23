package com.tg.attendance.module;

import com.google.inject.AbstractModule;
import com.tg.attendance.context.MfbDSL;
import com.tg.attendance.context.MfbDSLContext;
import com.tg.attendance.service.AttendancePersistanceService;
import com.tg.attendance.service.AttendancePersistanceServiceImpl;

public class AttendanceModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(AttendancePersistanceService.class).to(AttendancePersistanceServiceImpl.class);
        bind(MfbDSL.class).to(MfbDSLContext.class);
    }
}