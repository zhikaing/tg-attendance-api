package com.tg.attendance.service;


import com.google.inject.Inject;
import com.tg.attendance.context.DataSource;
import com.tg.attendance.context.MfbDSL;
import com.tg.attendance.dto.AttendanceDto;
import com.tg.attendance.dto.AttendanceSummaryOutput;
import com.tg.attendance.exception.BanyuanNotFoundException;
import com.tg.attendance.status.StatusCodeEnum;
import jooq.generated.tables.MfbAttendance;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public List<AttendanceSummaryOutput> attendanceSummary(String bentang) throws SQLException {
        if (bentang == null || bentang.equals("")) {
            return new ArrayList<>();
        }
        Connection con = null;
        try {
            con = DataSource.getDataSource().getConnection();
            con.prepareStatement("SET SESSION group_concat_max_len = 100000; ").execute();
            con.prepareStatement("select GROUP_CONCAT(DISTINCT CONCAT('MAX(CASE WHEN a.sdate = ''', sdate, ''' THEN `status_code_desc` ELSE 0 END) as ''',sdate,'''')) into @sql from mfb_attendance; ").execute();
            con.prepareStatement("SELECT CONCAT('SELECT b.deshu, b.name, ',@sql,' FROM mfb_banyuan b join mfb_attendance a on b.qrcode = a.qrcode join mfb_attendance_status_code c on a.status_code = c.status_code where a.sn = ''" + bentang + "'' and DATE_FORMAT(a.sdate, ''%Y'') = ', 2022,' group by b.deshu, b.name') into @pivot_statement; ").execute();
            con.prepareStatement("PREPARE complete_pivot_statment FROM  @pivot_statement; ").execute();
            ResultSet rs = con.prepareStatement("EXECUTE complete_pivot_statment;").executeQuery();
            List<AttendanceSummaryOutput> attendanceSummaryOutputList = new ArrayList<>();
            ResultSetMetaData metaData = rs.getMetaData();
            while (rs.next()) {
                Map<String, String> attendanceMap = new HashMap<>();
                int rowCount = metaData.getColumnCount();
                if (rowCount > 2) {
                    String deshu = rs.getString("deshu");
                    String name = rs.getString("name");
                    int dateCols = rowCount - 2;
                    attendanceMap.clear();
                    for(int i = 0; i < dateCols; i++) {
                        String colname = metaData.getColumnLabel(i + 2 + 1);
                        attendanceMap.put(colname, rs.getString(i + 2 + 1));
                    }
                    attendanceSummaryOutputList.add(new AttendanceSummaryOutput(name, deshu, attendanceMap));
                }
            }
            return attendanceSummaryOutputList;
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }
}
