package cn.tpson.kulu.gas.query;

import java.math.BigDecimal;

public class EqpDetectQuery extends BaseQuery {
    /**
     * 设备id.--> t_eqp.
     */
    private Integer eqpId;

    /**
     * 检测结果.
     */
    private BigDecimal detectValue;

    /**
     * 检测设备编号.
     */
    private String detectNo;

    /**
     * 状态，0：未提交；1：已提交；
     */
    private Short status;

    /**
     * 检测类型，0：进场检测；1：定期检测；
     */
    private Short type;

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
}