package com.tg.attendance.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AttendanceDto {
    private String id;
    private LocalDate date;
    private String bentang;

    public AttendanceDto(String id, String bentang) {
        this.id = id;
        this.bentang = bentang;
        this.date = LocalDate.now();
    }
}
