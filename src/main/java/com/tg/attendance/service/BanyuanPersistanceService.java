package com.tg.attendance.service;

import com.tg.attendance.dto.BanyuanDto;

public interface BanyuanPersistanceService {
    boolean checkIfBanyuanExist(String id) throws Exception;
    String getBanyuanName(String qrcode) throws Exception;
    String updateBanyuan(BanyuanDto banyuanDto) throws Exception;
    String updateBanyuanQRCode(String oldQrcode, String newQrcode) throws Exception;
}
