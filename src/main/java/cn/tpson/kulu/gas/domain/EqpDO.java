package cn.tpson.kulu.gas.domain;

/**
 * 设备实体.
 */
public class EqpDO extends BaseDO {
    /**
     * 设备标识号.
     */
    private String eqpNo;

    /**
     * 设备品牌.
     */
    private String brand;

    /**
     * 类型，1:推土机;2:挖掘机;3:旋挖钻;4:打桩机;5:其他;
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
     * 申请id.--> t_eqp_apply.
     */
    private Integer applyId;

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

    public Integer getApplyId() {
        return applyId;
    }

    public void setApplyId(Integer applyId) {
        this.applyId = applyId;
    }
}