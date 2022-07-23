/*
 * This file is generated by jOOQ.
 */
package jooq.generated.tables.records;


import jooq.generated.tables.MfbAttendanceStatusCode;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class MfbAttendanceStatusCodeRecord extends UpdatableRecordImpl<MfbAttendanceStatusCodeRecord> implements Record2<Integer, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>zdq.mfb_attendance_status_code.status_code</code>.
     */
    public void setStatusCode(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>zdq.mfb_attendance_status_code.status_code</code>.
     */
    public Integer getStatusCode() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>zdq.mfb_attendance_status_code.status_code_desc</code>.
     */
    public void setStatusCodeDesc(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>zdq.mfb_attendance_status_code.status_code_desc</code>.
     */
    public String getStatusCodeDesc() {
        return (String) get(1);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row2<Integer, String> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    @Override
    public Row2<Integer, String> valuesRow() {
        return (Row2) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return MfbAttendanceStatusCode.MFB_ATTENDANCE_STATUS_CODE.STATUS_CODE;
    }

    @Override
    public Field<String> field2() {
        return MfbAttendanceStatusCode.MFB_ATTENDANCE_STATUS_CODE.STATUS_CODE_DESC;
    }

    @Override
    public Integer component1() {
        return getStatusCode();
    }

    @Override
    public String component2() {
        return getStatusCodeDesc();
    }

    @Override
    public Integer value1() {
        return getStatusCode();
    }

    @Override
    public String value2() {
        return getStatusCodeDesc();
    }

    @Override
    public MfbAttendanceStatusCodeRecord value1(Integer value) {
        setStatusCode(value);
        return this;
    }

    @Override
    public MfbAttendanceStatusCodeRecord value2(String value) {
        setStatusCodeDesc(value);
        return this;
    }

    @Override
    public MfbAttendanceStatusCodeRecord values(Integer value1, String value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached MfbAttendanceStatusCodeRecord
     */
    public MfbAttendanceStatusCodeRecord() {
        super(MfbAttendanceStatusCode.MFB_ATTENDANCE_STATUS_CODE);
    }

    /**
     * Create a detached, initialised MfbAttendanceStatusCodeRecord
     */
    public MfbAttendanceStatusCodeRecord(Integer statusCode, String statusCodeDesc) {
        super(MfbAttendanceStatusCode.MFB_ATTENDANCE_STATUS_CODE);

        setStatusCode(statusCode);
        setStatusCodeDesc(statusCodeDesc);
    }
}