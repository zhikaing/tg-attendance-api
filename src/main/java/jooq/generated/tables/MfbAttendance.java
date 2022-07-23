/*
 * This file is generated by jOOQ.
 */
package jooq.generated.tables;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import jooq.generated.Keys;
import jooq.generated.Zdq;
import jooq.generated.tables.records.MfbAttendanceRecord;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row6;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class MfbAttendance extends TableImpl<MfbAttendanceRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>zdq.mfb_attendance</code>
     */
    public static final MfbAttendance MFB_ATTENDANCE = new MfbAttendance();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<MfbAttendanceRecord> getRecordType() {
        return MfbAttendanceRecord.class;
    }

    /**
     * The column <code>zdq.mfb_attendance.id</code>.
     */
    public final TableField<MfbAttendanceRecord, Integer> ID = createField(DSL.name("id"), SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>zdq.mfb_attendance.qrcode</code>.
     */
    public final TableField<MfbAttendanceRecord, String> QRCODE = createField(DSL.name("qrcode"), SQLDataType.VARCHAR(10).nullable(false), this, "");

    /**
     * The column <code>zdq.mfb_attendance.sn</code>.
     */
    public final TableField<MfbAttendanceRecord, String> SN = createField(DSL.name("sn"), SQLDataType.VARCHAR(2).nullable(false), this, "");

    /**
     * The column <code>zdq.mfb_attendance.sdate</code>.
     */
    public final TableField<MfbAttendanceRecord, LocalDate> SDATE = createField(DSL.name("sdate"), SQLDataType.LOCALDATE.nullable(false), this, "");

    /**
     * The column <code>zdq.mfb_attendance.status_code</code>.
     */
    public final TableField<MfbAttendanceRecord, Integer> STATUS_CODE = createField(DSL.name("status_code"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>zdq.mfb_attendance.created_ts</code>.
     */
    public final TableField<MfbAttendanceRecord, LocalDateTime> CREATED_TS = createField(DSL.name("created_ts"), SQLDataType.LOCALDATETIME(0).nullable(false), this, "");

    private MfbAttendance(Name alias, Table<MfbAttendanceRecord> aliased) {
        this(alias, aliased, null);
    }

    private MfbAttendance(Name alias, Table<MfbAttendanceRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>zdq.mfb_attendance</code> table reference
     */
    public MfbAttendance(String alias) {
        this(DSL.name(alias), MFB_ATTENDANCE);
    }

    /**
     * Create an aliased <code>zdq.mfb_attendance</code> table reference
     */
    public MfbAttendance(Name alias) {
        this(alias, MFB_ATTENDANCE);
    }

    /**
     * Create a <code>zdq.mfb_attendance</code> table reference
     */
    public MfbAttendance() {
        this(DSL.name("mfb_attendance"), null);
    }

    public <O extends Record> MfbAttendance(Table<O> child, ForeignKey<O, MfbAttendanceRecord> key) {
        super(child, key, MFB_ATTENDANCE);
    }

    @Override
    public Schema getSchema() {
        return Zdq.ZDQ;
    }

    @Override
    public Identity<MfbAttendanceRecord, Integer> getIdentity() {
        return (Identity<MfbAttendanceRecord, Integer>) super.getIdentity();
    }

    @Override
    public UniqueKey<MfbAttendanceRecord> getPrimaryKey() {
        return Keys.KEY_MFB_ATTENDANCE_PRIMARY;
    }

    @Override
    public List<UniqueKey<MfbAttendanceRecord>> getKeys() {
        return Arrays.<UniqueKey<MfbAttendanceRecord>>asList(Keys.KEY_MFB_ATTENDANCE_PRIMARY, Keys.KEY_MFB_ATTENDANCE_QRCODE);
    }

    @Override
    public MfbAttendance as(String alias) {
        return new MfbAttendance(DSL.name(alias), this);
    }

    @Override
    public MfbAttendance as(Name alias) {
        return new MfbAttendance(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public MfbAttendance rename(String name) {
        return new MfbAttendance(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public MfbAttendance rename(Name name) {
        return new MfbAttendance(name, null);
    }

    // -------------------------------------------------------------------------
    // Row6 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row6<Integer, String, String, LocalDate, Integer, LocalDateTime> fieldsRow() {
        return (Row6) super.fieldsRow();
    }
}
