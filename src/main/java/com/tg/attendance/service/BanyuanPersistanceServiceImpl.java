package com.tg.attendance.service;


import com.google.inject.Inject;
import com.tg.attendance.context.MfbDSL;
import com.tg.attendance.dto.BanyuanDto;
import jooq.generated.tables.MfbAttendance;
import jooq.generated.tables.MfbBanyuan;
import jooq.generated.tables.records.MfbBanyuanRecord;
import org.jooq.Configuration;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.impl.DSL;

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
        dsl.getContext().transaction((Configuration trx) -> {
            //update attendance QRcode
            trx.dsl().update(attendanceDef)
                    .set(attendanceDef.QRCODE, banyuanDto.getQrcode())
                    .where(attendanceDef.QRCODE.eq(
                            trx.dsl().select(banyuanDef.QRCODE)
                                    .from(banyuanDef)
                                    .where(banyuanDef.NAME.eq(banyuanDto.getName())))
                    ).execute();

            result.set(trx.dsl()
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
                    .where(banyuanDef.NAME.eq(banyuanDto.getName())).execute());
        });
        if (result.get() == 1) {
            return "更改成功！";
        } else {
            return "更改失败！";
        }
    }
}
