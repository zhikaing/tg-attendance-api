package com.tg.attendance.service;


import com.tg.attendance.dto.AttendanceDto;
import com.tg.attendance.dto.AttendanceSummaryOutput;

import java.sql.SQLException;
import java.util.List;

public interface AttendancePersistanceService {
    String mark(AttendanceDto attendanceDto) throws Exception;
    String takeLeave(AttendanceDto attendanceDto) throws Exception;
    List<AttendanceSummaryOutput> attendanceSummary(String bentang) throws SQLException;
}
