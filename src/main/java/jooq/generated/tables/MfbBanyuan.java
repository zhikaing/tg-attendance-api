/*
 * This file is generated by jOOQ.
 */
package jooq.generated.tables;


import java.util.Arrays;
import java.util.List;

import jooq.generated.Keys;
import jooq.generated.Zdq;
import jooq.generated.tables.records.MfbBanyuanRecord;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row12;
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
public class MfbBanyuan extends TableImpl<MfbBanyuanRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>zdq.mfb_banyuan</code>
     */
    public static final MfbBanyuan MFB_BANYUAN = new MfbBanyuan();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<MfbBanyuanRecord> getRecordType() {
        return MfbBanyuanRecord.class;
    }

    /**
     * The column <code>zdq.mfb_banyuan.id</code>.
     */
    public final TableField<MfbBanyuanRecord, Integer> ID = createField(DSL.name("id"), SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>zdq.mfb_banyuan.qrcode</code>.
     */
    public final TableField<MfbBanyuanRecord, String> QRCODE = createField(DSL.name("qrcode"), SQLDataType.VARCHAR(10).nullable(false), this, "");

    /**
     * The column <code>zdq.mfb_banyuan.name</code>.
     */
    public final TableField<MfbBanyuanRecord, String> NAME = createField(DSL.name("name"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>zdq.mfb_banyuan.gender</code>.
     */
    public final TableField<MfbBanyuanRecord, String> GENDER = createField(DSL.name("gender"), SQLDataType.VARCHAR(1).nullable(false), this, "");

    /**
     * The column <code>zdq.mfb_banyuan.deshu</code>.
     */
    public final TableField<MfbBanyuanRecord, String> DESHU = createField(DSL.name("deshu"), SQLDataType.VARCHAR(10).nullable(false), this, "");

    /**
     * The column <code>zdq.mfb_banyuan.fotang</code>.
     */
    public final TableField<MfbBanyuanRecord, String> FOTANG = createField(DSL.name("fotang"), SQLDataType.VARCHAR(50).nullable(false), this, "");

    /**
     * The column <code>zdq.mfb_banyuan.tianzhi</code>.
     */
    public final TableField<MfbBanyuanRecord, String> TIANZHI = createField(DSL.name("tianzhi"), SQLDataType.VARCHAR(20).nullable(false), this, "");

    /**
     * The column <code>zdq.mfb_banyuan.jcb_completion</code>.
     */
    public final TableField<MfbBanyuanRecord, String> JCB_COMPLETION = createField(DSL.name("jcb_completion"), SQLDataType.VARCHAR(1).nullable(false), this, "");

    /**
     * The column <code>zdq.mfb_banyuan.sn</code>.
     */
    public final TableField<MfbBanyuanRecord, String> SN = createField(DSL.name("sn"), SQLDataType.VARCHAR(2).nullable(false), this, "");

    /**
     * The column <code>zdq.mfb_banyuan.tel</code>.
     */
    public final TableField<MfbBanyuanRecord, String> TEL = createField(DSL.name("tel"), SQLDataType.VARCHAR(21).nullable(false), this, "");

    /**
     * The column <code>zdq.mfb_banyuan.status</code>.
     */
    public final TableField<MfbBanyuanRecord, String> STATUS = createField(DSL.name("status"), SQLDataType.VARCHAR(21).nullable(false), this, "");

    /**
     * The column <code>zdq.mfb_banyuan.remark</code>.
     */
    public final TableField<MfbBanyuanRecord, String> REMARK = createField(DSL.name("remark"), SQLDataType.VARCHAR(500).nullable(false), this, "");

    private MfbBanyuan(Name alias, Table<MfbBanyuanRecord> aliased) {
        this(alias, aliased, null);
    }

    private MfbBanyuan(Name alias, Table<MfbBanyuanRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>zdq.mfb_banyuan</code> table reference
     */
    public MfbBanyuan(String alias) {
        this(DSL.name(alias), MFB_BANYUAN);
    }

    /**
     * Create an aliased <code>zdq.mfb_banyuan</code> table reference
     */
    public MfbBanyuan(Name alias) {
        this(alias, MFB_BANYUAN);
    }

    /**
     * Create a <code>zdq.mfb_banyuan</code> table reference
     */
    public MfbBanyuan() {
        this(DSL.name("mfb_banyuan"), null);
    }

    public <O extends Record> MfbBanyuan(Table<O> child, ForeignKey<O, MfbBanyuanRecord> key) {
        super(child, key, MFB_BANYUAN);
    }

    @Override
    public Schema getSchema() {
        return Zdq.ZDQ;
    }

    @Override
    public Identity<MfbBanyuanRecord, Integer> getIdentity() {
        return (Identity<MfbBanyuanRecord, Integer>) super.getIdentity();
    }

    @Override
    public UniqueKey<MfbBanyuanRecord> getPrimaryKey() {
        return Keys.KEY_MFB_BANYUAN_PRIMARY;
    }

    @Override
    public List<UniqueKey<MfbBanyuanRecord>> getKeys() {
        return Arrays.<UniqueKey<MfbBanyuanRecord>>asList(Keys.KEY_MFB_BANYUAN_PRIMARY);
    }

    @Override
    public MfbBanyuan as(String alias) {
        return new MfbBanyuan(DSL.name(alias), this);
    }

    @Override
    public MfbBanyuan as(Name alias) {
        return new MfbBanyuan(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public MfbBanyuan rename(String name) {
        return new MfbBanyuan(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public MfbBanyuan rename(Name name) {
        return new MfbBanyuan(name, null);
    }

    // -------------------------------------------------------------------------
    // Row12 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row12<Integer, String, String, String, String, String, String, String, String, String, String, String> fieldsRow() {
        return (Row12) super.fieldsRow();
    }
}