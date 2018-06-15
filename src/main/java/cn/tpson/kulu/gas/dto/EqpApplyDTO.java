package cn.tpson.kulu.gas.dto;

import com.alibaba.fastjson.JSON;

import java.util.List;
import java.util.Map;

public class EqpApplyDTO extends BaseDTO {
    public static final short TYPE_INSTALL = 1;
    public static final short TYPE_UNINSTALL = 2;

    public static final short STATUS_NORMAL = 0;
    public static final short STATUS_WAITING = 1;
    public static final short STATUS_REFUSE = 2;
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
     * 状态，0：正常；1：待审核；2：拒绝
     */
    private Short status;

    /**
     * 类型，1：装机；2：拆机；
     */
    private Short type;

    /**
     * 备注.
     */
    private String comment;

    //////////////////////////////////////////////////////////////////////////////////////////////
    private List<EqpDTO> eqps;
    private LocationDTO gps;

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