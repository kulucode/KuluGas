package cn.tpson.kulu.gas.dto;

import cn.tpson.kulu.gas.json.InstantSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.Instant;

public class EqpDetectDTO extends BaseDTO {
    public static final short STATUS_WAITING = 0;
    public static final short STATUS_SERVICE = 1;
    public static final short STATUS_GOV = 2;

    public static final short TYPE_FIRST = 0;
    public static final short TYPE_USUAL = 1;
    /**
     * 设备id.--> t_eqp.
     */
    @NotNull(message = "设备ID不能为空.")
    private Integer eqpId;

    /**
     * 检测结果.
     */
    @NotNull(message = "检测结果不能为空.")
    private BigDecimal detectValue;

    /**
     * 检测设备编号.
     */
    @NotBlank(message = "检测编号不能为空.")
    private String detectNo;

    /**
     * 状态，0：未提交；1：提交服务公司；2：提交政府部门；
     */
    private Short status;

    /**
     * 检测类型，0：进场检测；1：日常检测；
     */
    private Short type;

    /**
     * 检测人.
     */
    private Integer uid;

    /**
     * 关联日志表id.
     */
    private Integer logId;

    /**
     * 关联日志表id.
     */
    private Integer lastLogId;

    /**
     * 检测时间.
     */
    @JsonSerialize(using = InstantSerializer.class)
    private Instant gmtDetect;
    /**
     * 上次检测时间.
     */
    @JsonSerialize(using = InstantSerializer.class)
    private Instant gmtLastDetect;

    private Double lat;
    private Double lon;

    ////////////////////////////////////////////////////////////
    /**
     * 被检测设备.
     */
    private EqpDTO eqp;

    private SysUserDTO user;

    private Integer[] ids;

    public EqpDetectDTO() {}

    public EqpDetectDTO(Integer id, Boolean deleted, Instant gmtCreate, Instant gmtModified) {
        super(id, deleted, gmtCreate, gmtModified);
    }

    public Integer getEqpId() {
        return eqpId;
    }

    public void setEqpId(Integer eqpId) {
        this.eqpId = eqpId;
    }

    public BigDecimal getDetectValue() {
        return detectValue;
    }

    public void setDetectValue(BigDecimal detectValue) {
        this.detectValue = detectValue;
    }

    public String getDetectNo() {
        return detectNo;
    }

    public void setDetectNo(String detectNo) {
        this.detectNo = detectNo;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public EqpDTO getEqp() {
        return eqp;
    }

    public void setEqp(EqpDTO eqp) {
        this.eqp = eqp;
    }

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public Integer getLastLogId() {
        return lastLogId;
    }

    public void setLastLogId(Integer lastLogId) {
        this.lastLogId = lastLogId;
    }

    public Instant getGmtDetect() {
        return gmtDetect;
    }

    public void setGmtDetect(Instant gmtDetect) {
        this.gmtDetect = gmtDetect;
    }

    public Instant getGmtLastDetect() {
        return gmtLastDetect;
    }

    public void setGmtLastDetect(Instant gmtLastDetect) {
        this.gmtLastDetect = gmtLastDetect;
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

    public Integer[] getIds() {
        return ids;
    }

    public void setIds(Integer[] ids) {
        this.ids = ids;
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
}