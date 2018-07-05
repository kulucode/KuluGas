package cn.tpson.kulu.gas.query;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class EqpQuery extends BaseQuery {
    /**
     * 设备标识号.
     */
    private String eqpNo;

    /**
     * 设备品牌.
     */
    private String brand;

    /**
     * 类型
     */
    private Short type;

    /**
     * 机龄.
     */
    private Short age;

    /**
     * 铭牌.
     */
    private String dataPlate;

    /**
     * 机主名称.
     */
    private String ownerName;

    /**
     * 申请者id.
     */
    private Integer uid;

    /**
     * 状态.
     * @see cn.tpson.kulu.gas.constant.EqpStatusEnum
     */
    private Short status;

    /**
     * 所属工地名称.
     */
    private String bsName;

    /**
     * 所属工地id.
     */
    private Integer bsId;

    /**
     * 排放等级.
     */
    private String disLevel;

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    /**
     * 搜索关键字.
     */
    private String key;

    /**
     * 开始时间(用户时间查询).
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date st;

    /**
     * 截止时间(用户时间查询).
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date et;

    /**
     * 区域.(省,市,区)
     */
    private String area;

    private Integer[] ids;

    public String getEqpNo() {
        return eqpNo;
    }

    public void setEqpNo(String eqpNo) {
        this.eqpNo = eqpNo;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public Short getAge() {
        return age;
    }

    public void setAge(Short age) {
        this.age = age;
    }

    public String getDataPlate() {
        return dataPlate;
    }

    public void setDataPlate(String dataPlate) {
        this.dataPlate = dataPlate;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Integer[] getIds() {
        return ids;
    }

    public void setIds(Integer[] ids) {
        this.ids = ids;
    }

    public String getBsName() {
        return bsName;
    }

    public void setBsName(String bsName) {
        this.bsName = bsName;
    }

    public Integer getBsId() {
        return bsId;
    }

    public void setBsId(Integer bsId) {
        this.bsId = bsId;
    }

    public String getDisLevel() {
        return disLevel;
    }

    public void setDisLevel(String disLevel) {
        this.disLevel = disLevel;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Date getSt() {
        return st;
    }

    public void setSt(Date st) {
        this.st = st;
    }

    public Date getEt() {
        return et;
    }

    public void setEt(Date et) {
        this.et = et;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}