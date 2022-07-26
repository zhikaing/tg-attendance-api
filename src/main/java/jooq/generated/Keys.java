/*
 * This file is generated by jOOQ.
 */
package jooq.generated;


import jooq.generated.tables.MfbAttendance;
import jooq.generated.tables.MfbAttendanceStatusCode;
import jooq.generated.tables.MfbBanyuan;
import jooq.generated.tables.MfbBentang;
import jooq.generated.tables.records.MfbAttendanceRecord;
import jooq.generated.tables.records.MfbAttendanceStatusCodeRecord;
import jooq.generated.tables.records.MfbBanyuanRecord;
import jooq.generated.tables.records.MfbBentangRecord;

import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables in 
 * zdq.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<MfbAttendanceRecord> KEY_MFB_ATTENDANCE_PRIMARY = Internal.createUniqueKey(MfbAttendance.MFB_ATTENDANCE, DSL.name("KEY_mfb_attendance_PRIMARY"), new TableField[] { MfbAttendance.MFB_ATTENDANCE.ID }, true);
    public static final UniqueKey<MfbAttendanceRecord> KEY_MFB_ATTENDANCE_QRCODE = Internal.createUniqueKey(MfbAttendance.MFB_ATTENDANCE, DSL.name("KEY_mfb_attendance_qrcode"), new TableField[] { MfbAttendance.MFB_ATTENDANCE.QRCODE, MfbAttendance.MFB_ATTENDANCE.SDATE }, true);
    public static final UniqueKey<MfbAttendanceStatusCodeRecord> KEY_MFB_ATTENDANCE_STATUS_CODE_PRIMARY = Internal.createUniqueKey(MfbAttendanceStatusCode.MFB_ATTENDANCE_STATUS_CODE, DSL.name("KEY_mfb_attendance_status_code_PRIMARY"), new TableField[] { MfbAttendanceStatusCode.MFB_ATTENDANCE_STATUS_CODE.STATUS_CODE }, true);
    public static final UniqueKey<MfbBanyuanRecord> KEY_MFB_BANYUAN_PRIMARY = Internal.createUniqueKey(MfbBanyuan.MFB_BANYUAN, DSL.name("KEY_mfb_banyuan_PRIMARY"), new TableField[] { MfbBanyuan.MFB_BANYUAN.ID }, true);
    public static final UniqueKey<MfbBentangRecord> KEY_MFB_BENTANG_PRIMARY = Internal.createUniqueKey(MfbBentang.MFB_BENTANG, DSL.name("KEY_mfb_bentang_PRIMARY"), new TableField[] { MfbBentang.MFB_BENTANG.SN }, true);
}
