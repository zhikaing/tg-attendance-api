/*
 * This file is generated by jOOQ.
 */
package jooq.generated.tables.records;


import jooq.generated.tables.MfbBanyuan;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record12;
import org.jooq.Row12;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class MfbBanyuanRecord extends UpdatableRecordImpl<MfbBanyuanRecord> implements Record12<Integer, String, String, String, String, String, String, String, String, String, String, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>zdq.mfb_banyuan.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>zdq.mfb_banyuan.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>zdq.mfb_banyuan.qrcode</code>.
     */
    public void setQrcode(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>zdq.mfb_banyuan.qrcode</code>.
     */
    public String getQrcode() {
        return (String) get(1);
    }

    /**
     * Setter for <code>zdq.mfb_banyuan.name</code>.
     */
    public void setName(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>zdq.mfb_banyuan.name</code>.
     */
    public String getName() {
        return (String) get(2);
    }

    /**
     * Setter for <code>zdq.mfb_banyuan.gender</code>.
     */
    public void setGender(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>zdq.mfb_banyuan.gender</code>.
     */
    public String getGender() {
        return (String) get(3);
    }

    /**
     * Setter for <code>zdq.mfb_banyuan.deshu</code>.
     */
    public void setDeshu(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>zdq.mfb_banyuan.deshu</code>.
     */
    public String getDeshu() {
        return (String) get(4);
    }

    /**
     * Setter for <code>zdq.mfb_banyuan.fotang</code>.
     */
    public void setFotang(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>zdq.mfb_banyuan.fotang</code>.
     */
    public String getFotang() {
        return (String) get(5);
    }

    /**
     * Setter for <code>zdq.mfb_banyuan.tianzhi</code>.
     */
    public void setTianzhi(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>zdq.mfb_banyuan.tianzhi</code>.
     */
    public String getTianzhi() {
        return (String) get(6);
    }

    /**
     * Setter for <code>zdq.mfb_banyuan.jcb_completion</code>.
     */
    public void setJcbCompletion(String value) {
        set(7, value);
    }

    /**
     * Getter for <code>zdq.mfb_banyuan.jcb_completion</code>.
     */
    public String getJcbCompletion() {
        return (String) get(7);
    }

    /**
     * Setter for <code>zdq.mfb_banyuan.sn</code>.
     */
    public void setSn(String value) {
        set(8, value);
    }

    /**
     * Getter for <code>zdq.mfb_banyuan.sn</code>.
     */
    public String getSn() {
        return (String) get(8);
    }

    /**
     * Setter for <code>zdq.mfb_banyuan.tel</code>.
     */
    public void setTel(String value) {
        set(9, value);
    }

    /**
     * Getter for <code>zdq.mfb_banyuan.tel</code>.
     */
    public String getTel() {
        return (String) get(9);
    }

    /**
     * Setter for <code>zdq.mfb_banyuan.status</code>.
     */
    public void setStatus(String value) {
        set(10, value);
    }

    /**
     * Getter for <code>zdq.mfb_banyuan.status</code>.
     */
    public String getStatus() {
        return (String) get(10);
    }

    /**
     * Setter for <code>zdq.mfb_banyuan.remark</code>.
     */
    public void setRemark(String value) {
        set(11, value);
    }

    /**
     * Getter for <code>zdq.mfb_banyuan.remark</code>.
     */
    public String getRemark() {
        return (String) get(11);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record12 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row12<Integer, String, String, String, String, String, String, String, String, String, String, String> fieldsRow() {
        return (Row12) super.fieldsRow();
    }

    @Override
    public Row12<Integer, String, String, String, String, String, String, String, String, String, String, String> valuesRow() {
        return (Row12) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return MfbBanyuan.MFB_BANYUAN.ID;
    }

    @Override
    public Field<String> field2() {
        return MfbBanyuan.MFB_BANYUAN.QRCODE;
    }

    @Override
    public Field<String> field3() {
        return MfbBanyuan.MFB_BANYUAN.NAME;
    }

    @Override
    public Field<String> field4() {
        return MfbBanyuan.MFB_BANYUAN.GENDER;
    }

    @Override
    public Field<String> field5() {
        return MfbBanyuan.MFB_BANYUAN.DESHU;
    }

    @Override
    public Field<String> field6() {
        return MfbBanyuan.MFB_BANYUAN.FOTANG;
    }

    @Override
    public Field<String> field7() {
        return MfbBanyuan.MFB_BANYUAN.TIANZHI;
    }

    @Override
    public Field<String> field8() {
        return MfbBanyuan.MFB_BANYUAN.JCB_COMPLETION;
    }

    @Override
    public Field<String> field9() {
        return MfbBanyuan.MFB_BANYUAN.SN;
    }

    @Override
    public Field<String> field10() {
        return MfbBanyuan.MFB_BANYUAN.TEL;
    }

    @Override
    public Field<String> field11() {
        return MfbBanyuan.MFB_BANYUAN.STATUS;
    }

    @Override
    public Field<String> field12() {
        return MfbBanyuan.MFB_BANYUAN.REMARK;
    }

    @Override
    public Integer component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getQrcode();
    }

    @Override
    public String component3() {
        return getName();
    }

    @Override
    public String component4() {
        return getGender();
    }

    @Override
    public String component5() {
        return getDeshu();
    }

    @Override
    public String component6() {
        return getFotang();
    }

    @Override
    public String component7() {
        return getTianzhi();
    }

    @Override
    public String component8() {
        return getJcbCompletion();
    }

    @Override
    public String component9() {
        return getSn();
    }

    @Override
    public String component10() {
        return getTel();
    }

    @Override
    public String component11() {
        return getStatus();
    }

    @Override
    public String component12() {
        return getRemark();
    }

    @Override
    public Integer value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getQrcode();
    }

    @Override
    public String value3() {
        return getName();
    }

    @Override
    public String value4() {
        return getGender();
    }

    @Override
    public String value5() {
        return getDeshu();
    }

    @Override
    public String value6() {
        return getFotang();
    }

    @Override
    public String value7() {
        return getTianzhi();
    }

    @Override
    public String value8() {
        return getJcbCompletion();
    }

    @Override
    public String value9() {
        return getSn();
    }

    @Override
    public String value10() {
        return getTel();
    }

    @Override
    public String value11() {
        return getStatus();
    }

    @Override
    public String value12() {
        return getRemark();
    }

    @Override
    public MfbBanyuanRecord value1(Integer value) {
        setId(value);
        return this;
    }

    @Override
    public MfbBanyuanRecord value2(String value) {
        setQrcode(value);
        return this;
    }

    @Override
    public MfbBanyuanRecord value3(String value) {
        setName(value);
        return this;
    }

    @Override
    public MfbBanyuanRecord value4(String value) {
        setGender(value);
        return this;
    }

    @Override
    public MfbBanyuanRecord value5(String value) {
        setDeshu(value);
        return this;
    }

    @Override
    public MfbBanyuanRecord value6(String value) {
        setFotang(value);
        return this;
    }

    @Override
    public MfbBanyuanRecord value7(String value) {
        setTianzhi(value);
        return this;
    }

    @Override
    public MfbBanyuanRecord value8(String value) {
        setJcbCompletion(value);
        return this;
    }

    @Override
    public MfbBanyuanRecord value9(String value) {
        setSn(value);
        return this;
    }

    @Override
    public MfbBanyuanRecord value10(String value) {
        setTel(value);
        return this;
    }

    @Override
    public MfbBanyuanRecord value11(String value) {
        setStatus(value);
        return this;
    }

    @Override
    public MfbBanyuanRecord value12(String value) {
        setRemark(value);
        return this;
    }

    @Override
    public MfbBanyuanRecord values(Integer value1, String value2, String value3, String value4, String value5, String value6, String value7, String value8, String value9, String value10, String value11, String value12) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        value10(value10);
        value11(value11);
        value12(value12);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached MfbBanyuanRecord
     */
    public MfbBanyuanRecord() {
        super(MfbBanyuan.MFB_BANYUAN);
    }

    /**
     * Create a detached, initialised MfbBanyuanRecord
     */
    public MfbBanyuanRecord(Integer id, String qrcode, String name, String gender, String deshu, String fotang, String tianzhi, String jcbCompletion, String sn, String tel, String status, String remark) {
        super(MfbBanyuan.MFB_BANYUAN);

        setId(id);
        setQrcode(qrcode);
        setName(name);
        setGender(gender);
        setDeshu(deshu);
        setFotang(fotang);
        setTianzhi(tianzhi);
        setJcbCompletion(jcbCompletion);
        setSn(sn);
        setTel(tel);
        setStatus(status);
        setRemark(remark);
    }
}