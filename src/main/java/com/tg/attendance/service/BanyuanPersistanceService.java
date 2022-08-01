package com.tg.attendance.service;

import com.tg.attendance.dto.BanyuanDto;
import com.tg.attendance.exception.BanyuanNotFoundException;

import java.util.List;

public interface BanyuanPersistanceService {
    boolean checkIfBanyuanExist(String id) throws Exception;
    String getBanyuanName(String qrcode) throws Exception;
    String updateBanyuan(BanyuanDto banyuanDto) throws Exception;
    String createBanyuan(BanyuanDto banyuanDto) throws Exception;
    String updateBanyuanQRCode(String oldQrcode, String newQrcode) throws Exception;
    List<BanyuanDto> fetchAllBanyuans(String bentang) throws Exception;
    BanyuanDto findBanyuanById(String id) throws BanyuanNotFoundException, Exception;
}
