package com.tg.attendance.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BanyuanDto {
    private String qrcode;
    private String name;
    private String gender;
    private String deshu;
    private String fotang;
    private String tianzhi;
    private String jcbCompletion;
    private String sn;
    private String tel;
    private String status;
    private String remark;
}
