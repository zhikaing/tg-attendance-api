package com.tg.attendance.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

@Data
public class AttendanceSummaryOutput extends LinkedHashMap {
    public AttendanceSummaryOutput(Map<String, Object> items) {
        super.putAll(items);
    }
}
