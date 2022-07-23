package com.tg.attendance.service;


import com.google.inject.Inject;
import com.tg.attendance.context.MfbDSL;
import com.tg.attendance.dto.AttendanceDto;
import com.tg.attendance.exception.BanyuanNotFoundException;
import com.tg.attendance.status.StatusCodeEnum;
import jooq.generated.tables.MfbAttendance;
import jooq.generated.tables.MfbBanyuan;

import java.time.LocalDateTime;

public class AttendancePersistanceServiceImpl implements AttendancePersistanceService {
    private MfbDSL dsl;
    private BanyuanPersistanceService banyuanPersistanceService;
    private MfbAttendance attendanceDef = MfbAttendance.MFB_ATTENDANCE;

    @Inject
    public AttendancePersistanceServiceImpl(MfbDSL dsl, BanyuanPersistanceService banyuanPersistanceService) {
        this.dsl = dsl;
        this.banyuanPersistanceService = banyuanPersistanceService;
    }

    @Override
    public String mark(AttendanceDto attendanceDto) throws Exception {
        boolean isExisting = banyuanPersistanceService.checkIfBanyuanExist(attendanceDto.getId());
        if (isExisting) {
            int result = dsl.getContext()
                    .insertInto(attendanceDef,
                            attendanceDef.QRCODE,
                            attendanceDef.SN,
                            attendanceDef.STATUS_CODE,
                            attendanceDef.SDATE,
                            attendanceDef.CREATED_TS)
                    .values(
                            attendanceDto.getId(),
                            attendanceDto.getBentang(),
                            StatusCodeEnum.PRESENT.getStatusCode(),
                            attendanceDto.getDate(),
                            LocalDateTime.now()
                    ).onConflict(attendanceDef.QRCODE, attendanceDef.SDATE).doNothing().execute();
            String name = null;
           switch (result) {
               case 1:
                   name = banyuanPersistanceService.getBanyuanName(attendanceDto.getId());
                   return name + ", 签到成功！";
               case 0:
                   name = banyuanPersistanceService.getBanyuanName(attendanceDto.getId());
                   return name + "已签到过了。";
               default:
                   return attendanceDto.getId() + "帐号有问题！";
           }

        } else {
            throw new BanyuanNotFoundException(String.format("没有档案: %s", attendanceDto.getId()));
        }
    }
}
