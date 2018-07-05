package cn.tpson.kulu.gas.dto;

import java.time.Instant;
import java.util.List;

public class EqpApplyDTO extends BaseDTO {
    /**
     * 申请人id.--> t_sys_user.
     */
    private Integer uid;

    /**
     * 联系电话.
     */
    private String phoneNo;

    /**
     * 装机地址.
     */
    private String addr;

    /**
     * 定位地址.
     */
    private String location;

    /**
     * 备注.
     */
    private String comment;

    //////////////////////////////////////////////////////////////////////////////////////////////
    private List<EqpDTO> eqps;
    private LocationDTO gps;

    public EqpApplyDTO() {}

    public EqpApplyDTO(Integer id, Boolean deleted, Instant gmtCreate, Instant gmtModified) {
        super(id, deleted, gmtCreate, gmtModified);
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<EqpDTO> getEqps() {
        return eqps;
    }

    public void setEqps(List<EqpDTO> eqps) {
        this.eqps = eqps;
    }

    public LocationDTO getGps() {
        return gps;
    }

    public void setGps(LocationDTO gps) {
        this.gps = gps;
    }
}