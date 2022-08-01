package com.tg.attendance.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class BanyuanDto {
    private int id;
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
    public BanyuanDto(String qrcode, String name, String gender, String deshu, String fotang, String tianzhi, String jcbCompletion,
                      String sn, String tel, String status, String remark) {
        this.qrcode = qrcode;
        this.name = name;
        this.gender = gender;
        this.deshu = deshu;
        this.fotang = fotang;
        this.tianzhi = tianzhi;
        this.jcbCompletion = jcbCompletion;
        this.sn = sn;
        this.tel = tel;
        this.status = status;
        this.remark = remark;
    }
    public BanyuanDto(String id, String qrcode, String name, String gender, String deshu, String fotang, String tianzhi, String jcbCompletion,
                      String sn, String tel, String status, String remark) {
        this.id = Integer.parseInt(id);
        this.qrcode = qrcode;
        this.name = name;
        this.gender = gender;
        this.deshu = deshu;
        this.fotang = fotang;
        this.tianzhi = tianzhi;
        this.jcbCompletion = jcbCompletion;
        this.sn = sn;
        this.tel = tel;
        this.status = status;
        this.remark = remark;
    }
}
