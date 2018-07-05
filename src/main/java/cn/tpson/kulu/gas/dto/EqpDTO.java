package cn.tpson.kulu.gas.dto;

import cn.tpson.kulu.gas.json.InstantSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.Instant;

public class EqpDTO extends BaseDTO {
    /**
     * 检测工时阀值.
     */
    public static final int EQP_DETECT_THRESHOLD = 6000;
    /**
     * 设备标识号.
     */
    private String eqpNo;

    /**
     * 设备品牌.
     */
    private String brand;

    /**
     * 类型.
     * @see cn.tpson.kulu.gas.constant.EqpTypeEnum;
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
     * 用户id.
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

    ///////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////
    /**
     * 机主证件号.
     */
    private String idCard;
    /**
     * 联系电话.
     */
    private String phoneNo;

    /**
     * 最后检测时间.
     */
    @JsonSerialize(using = InstantSerializer.class)
    private Instant gmtDetect;

    /**
     *  最后一次检测结果.
     */
    private Double detectValue;

    /**
     * 工时.
     */
    private Integer workingHours;

    /**
     * 年度未检次数.
     */
    private Integer undetectCount;

    /**
     * 年度处罚次数.
     */
    private Integer punishCount;

    /**
     *  车辆实时纬度.
     */
    private Double lat;

    /**
     * 车辆实时经度.
     */
    private Double lon;

    private SysUserDTO user;

    private Integer[] ids;

    private SysUserBuildingSiteDTO bs;

    public EqpDTO() {}

    public EqpDTO(Integer id, Boolean deleted, Instant gmtCreate, Instant gmtModified) {
        super(id, deleted, gmtCreate, gmtModified);
    }

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

    public SysUserDTO getUser() {
        return user;
    }

    public void setUser(SysUserDTO user) {
        this.user = user;
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

    public SysUserBuildingSiteDTO getBs() {
        return bs;
    }

    public void setBs(SysUserBuildingSiteDTO bs) {
        this.bs = bs;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public Instant getGmtDetect() {
        return gmtDetect;
    }

    public void setGmtDetect(Instant gmtDetect) {
        this.gmtDetect = gmtDetect;
    }

    public Integer getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(Integer workingHours) {
        this.workingHours = workingHours;
    }

    public Integer getUndetectCount() {
        return undetectCount;
    }

    public void setUndetectCount(Integer undetectCount) {
        this.undetectCount = undetectCount;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public String getDisLevel() {
        return disLevel;
    }

    public void setDisLevel(String disLevel) {
        this.disLevel = disLevel;
    }

    public Double getDetectValue() {
        return detectValue;
    }

    public void setDetectValue(Double detectValue) {
        this.detectValue = detectValue;
    }

    public Integer getPunishCount() {
        return punishCount;
    }

    public void setPunishCount(Integer punishCount) {
        this.punishCount = punishCount;
    }
}