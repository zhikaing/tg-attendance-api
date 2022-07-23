package com.tg.attendance.service;


import com.tg.attendance.dto.AttendanceDto;

public interface AttendancePersistanceService {
    String mark(AttendanceDto attendanceDto) throws Exception;
}
