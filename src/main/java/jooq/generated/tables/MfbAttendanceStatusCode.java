/*
 * This file is generated by jOOQ.
 */
package jooq.generated.tables;


import java.util.Arrays;
import java.util.List;

import jooq.generated.Keys;
import jooq.generated.Zdq;
import jooq.generated.tables.records.MfbAttendanceStatusCodeRecord;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row2;
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
public class MfbAttendanceStatusCode extends TableImpl<MfbAttendanceStatusCodeRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>zdq.mfb_attendance_status_code</code>
     */
    public static final MfbAttendanceStatusCode MFB_ATTENDANCE_STATUS_CODE = new MfbAttendanceStatusCode();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<MfbAttendanceStatusCodeRecord> getRecordType() {
        return MfbAttendanceStatusCodeRecord.class;
    }

    /**
     * The column <code>zdq.mfb_attendance_status_code.status_code</code>.
     */
    public final TableField<MfbAttendanceStatusCodeRecord, Integer> STATUS_CODE = createField(DSL.name("status_code"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>zdq.mfb_attendance_status_code.status_code_desc</code>.
     */
    public final TableField<MfbAttendanceStatusCodeRecord, String> STATUS_CODE_DESC = createField(DSL.name("status_code_desc"), SQLDataType.VARCHAR(100).nullable(false), this, "");

    private MfbAttendanceStatusCode(Name alias, Table<MfbAttendanceStatusCodeRecord> aliased) {
        this(alias, aliased, null);
    }

    private MfbAttendanceStatusCode(Name alias, Table<MfbAttendanceStatusCodeRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>zdq.mfb_attendance_status_code</code> table reference
     */
    public MfbAttendanceStatusCode(String alias) {
        this(DSL.name(alias), MFB_ATTENDANCE_STATUS_CODE);
    }

    /**
     * Create an aliased <code>zdq.mfb_attendance_status_code</code> table reference
     */
    public MfbAttendanceStatusCode(Name alias) {
        this(alias, MFB_ATTENDANCE_STATUS_CODE);
    }

    /**
     * Create a <code>zdq.mfb_attendance_status_code</code> table reference
     */
    public MfbAttendanceStatusCode() {
        this(DSL.name("mfb_attendance_status_code"), null);
    }

    public <O extends Record> MfbAttendanceStatusCode(Table<O> child, ForeignKey<O, MfbAttendanceStatusCodeRecord> key) {
        super(child, key, MFB_ATTENDANCE_STATUS_CODE);
    }

    @Override
    public Schema getSchema() {
        return Zdq.ZDQ;
    }

    @Override
    public UniqueKey<MfbAttendanceStatusCodeRecord> getPrimaryKey() {
        return Keys.KEY_MFB_ATTENDANCE_STATUS_CODE_PRIMARY;
    }

    @Override
    public List<UniqueKey<MfbAttendanceStatusCodeRecord>> getKeys() {
        return Arrays.<UniqueKey<MfbAttendanceStatusCodeRecord>>asList(Keys.KEY_MFB_ATTENDANCE_STATUS_CODE_PRIMARY);
    }

    @Override
    public MfbAttendanceStatusCode as(String alias) {
        return new MfbAttendanceStatusCode(DSL.name(alias), this);
    }

    @Override
    public MfbAttendanceStatusCode as(Name alias) {
        return new MfbAttendanceStatusCode(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public MfbAttendanceStatusCode rename(String name) {
        return new MfbAttendanceStatusCode(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public MfbAttendanceStatusCode rename(Name name) {
        return new MfbAttendanceStatusCode(name, null);
    }

    // -------------------------------------------------------------------------
    // Row2 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row2<Integer, String> fieldsRow() {
        return (Row2) super.fieldsRow();
    }
}