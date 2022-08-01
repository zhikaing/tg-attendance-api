package com.tg.attendance.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.tg.attendance.context.MfbDSL;
import com.tg.attendance.dto.BanyuanDto;
import com.tg.attendance.exception.BanyuanNotFoundException;
import jooq.generated.tables.MfbAttendance;
import jooq.generated.tables.MfbBanyuan;
import jooq.generated.tables.records.MfbBanyuanRecord;
import org.jooq.Configuration;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.impl.DSL;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class BanyuanPersistanceServiceImpl implements BanyuanPersistanceService {
    private MfbDSL dsl;
    private MfbBanyuan banyuanDef = MfbBanyuan.MFB_BANYUAN;
    private MfbAttendance attendanceDef = MfbAttendance.MFB_ATTENDANCE;
    @Inject
    public BanyuanPersistanceServiceImpl (MfbDSL dsl) {
        this.dsl = dsl;
    }
    @Override
    public boolean checkIfBanyuanExist(String qrcode) throws Exception {
        int count = dsl.getContext().fetchCount(DSL.selectFrom(banyuanDef).where(banyuanDef.QRCODE.eq(qrcode)));
        return count > 0;
    }
    @Override
    public String getBanyuanName(String qrcode) throws Exception {
        Result<Record> banyuanResult = dsl.getContext().select().from(banyuanDef).where(banyuanDef.QRCODE.eq(qrcode)).fetch();
        if(banyuanResult.isNotEmpty()) {
            return ((MfbBanyuanRecord)(banyuanResult.iterator().next())).getName();
        } else {
            return null;
        }
    }

    @Override
    public List<BanyuanDto> fetchAllBanyuans(String bentang) throws Exception {
        Result<Record> banyuanResult = dsl.getContext().select().from(banyuanDef).where(banyuanDef.SN.eq(bentang)).fetch();
        if(banyuanResult.isNotEmpty()) {
            Iterator result = banyuanResult.iterator();
            List<BanyuanDto> banyuanDtos = new ArrayList<>();
            while(result.hasNext()) {
                MfbBanyuanRecord by = (MfbBanyuanRecord)result.next();
                banyuanDtos.add(new BanyuanDto(
                        by.getId(),
                        by.getQrcode(),
                        by.getName(),
                        by.getGender(),
                        by.getDeshu(),
                        by.getFotang(),
                        by.getTianzhi(),
                        by.getJcbCompletion(),
                        by.getSn(),
                        by.getTel(),
                        by.getStatus(),
                        by.getRemark())
                );
            }
            return banyuanDtos;
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public BanyuanDto findBanyuanById(String id) throws BanyuanNotFoundException, Exception {
        Result<Record> banyuanResult = dsl.getContext().select().from(banyuanDef).where(banyuanDef.ID.eq(Integer.parseInt(id))).fetch();
        if(banyuanResult.isNotEmpty()) {
            Iterator result = banyuanResult.iterator();
            MfbBanyuanRecord by = (MfbBanyuanRecord)result.next();
            return new BanyuanDto(
                        by.getId(),
                        by.getQrcode(),
                        by.getName(),
                        by.getGender(),
                        by.getDeshu(),
                        by.getFotang(),
                        by.getTianzhi(),
                        by.getJcbCompletion(),
                        by.getSn(),
                        by.getTel(),
                        by.getStatus(),
                        by.getRemark());
        }
        throw new BanyuanNotFoundException("Banyuan does not exist, ID : "+id);
    }

    @Override
    public String updateBanyuanQRCode(String oldQrcode, String newQrcode) throws Exception {
        AtomicInteger result = new AtomicInteger(0);
        dsl.getContext().transaction((Configuration trx) -> {
            //update attendance QRcode
            trx.dsl().update(attendanceDef)
                    .set(attendanceDef.QRCODE, newQrcode)
                    .where(attendanceDef.QRCODE.eq(oldQrcode)
                    ).execute();

            result.set(trx.dsl()
                    .update(banyuanDef)
                    .set(banyuanDef.QRCODE, newQrcode)
                    .where(banyuanDef.QRCODE.eq(oldQrcode)).execute());
        });
        if (result.get() == 1) {
            return "更改成功！";
        } else {
            return "更改失败！";
        }
    }


    @Override
    public String updateBanyuan(BanyuanDto banyuanDto) throws Exception {
        AtomicInteger result = new AtomicInteger(0);
            //update attendance QRcode
        dsl.getContext().update(attendanceDef)
                    .set(attendanceDef.QRCODE, banyuanDto.getQrcode())
                    .where(attendanceDef.QRCODE.eq(
                            dsl.getContext().select(banyuanDef.QRCODE)
                                    .from(banyuanDef)
                                    .where(banyuanDef.ID.eq(banyuanDto.getId())))
                    ).execute();

            result.set(dsl.getContext()
                    .update(banyuanDef)
                    .set(banyuanDef.QRCODE, banyuanDto.getQrcode())
                    .set(banyuanDef.GENDER, banyuanDto.getGender())
                    .set(banyuanDef.DESHU, banyuanDto.getDeshu())
                    .set(banyuanDef.FOTANG, banyuanDto.getFotang())
                    .set(banyuanDef.TIANZHI, banyuanDto.getTianzhi())
                    .set(banyuanDef.JCB_COMPLETION, banyuanDto.getJcbCompletion())
                    .set(banyuanDef.SN, banyuanDto.getSn())
                    .set(banyuanDef.TEL, banyuanDto.getTel())
                    .set(banyuanDef.STATUS, banyuanDto.getStatus())
                    .set(banyuanDef.REMARK, banyuanDto.getRemark())
                    .where(banyuanDef.ID.eq(banyuanDto.getId())).execute());

        if (result.get() == 1) {
            return "更改成功！";
        } else {
            return "更改失败！";
        }
    }

    @Override
    public String createBanyuan(BanyuanDto banyuanDto) throws Exception {
        AtomicInteger result = new AtomicInteger(0);
        if (banyuanDto.getQrcode() != null && banyuanDto.getQrcode() != "" &&
                !checkIfBanyuanExist(banyuanDto.getQrcode())) {
            result.set(dsl.getContext().insertInto(banyuanDef,
                            banyuanDef.QRCODE,
                            banyuanDef.NAME,
                            banyuanDef.GENDER,
                            banyuanDef.DESHU,
                            banyuanDef.FOTANG,
                            banyuanDef.TIANZHI,
                            banyuanDef.JCB_COMPLETION,
                            banyuanDef.SN,
                            banyuanDef.TEL,
                            banyuanDef.STATUS,
                            banyuanDef.REMARK)
                    .values(banyuanDto.getQrcode(),
                            banyuanDto.getName(),
                            banyuanDto.getGender(),
                            banyuanDto.getDeshu(),
                            banyuanDto.getFotang(),
                            banyuanDto.getTianzhi(),
                            banyuanDto.getJcbCompletion(),
                            banyuanDto.getSn(),
                            banyuanDto.getTel(),
                            banyuanDto.getStatus(),
                            banyuanDto.getRemark())
                    .onConflict(banyuanDef.QRCODE).doNothing()
                    .execute());
        }
        if (result.get() == 1) {
            return "注册成功！";
        } else {
            return "注册失败！";
        }
    }
}
