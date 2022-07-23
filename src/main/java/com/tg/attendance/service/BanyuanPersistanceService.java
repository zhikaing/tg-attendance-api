package com.tg.attendance.service;

import com.tg.attendance.dto.BanyuanDto;

public interface BanyuanPersistanceService {
    boolean checkIfBanyuanExist(String name) throws Exception;
    String getBanyuanName(String qrcode) throws Exception;
    String updateBanyuan(BanyuanDto banyuanDto) throws Exception;
}
