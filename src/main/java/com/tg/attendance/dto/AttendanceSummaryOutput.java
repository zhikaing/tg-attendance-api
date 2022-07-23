package com.tg.attendance.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class AttendanceSummaryOutput {
    private String name;
    private String deshu;
    private Map<String, String> attendances;
}
