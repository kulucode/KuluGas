package cn.tpson.kulu.gas.query;

public class EqpApplyQuery extends BaseQuery {
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

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    private Integer[] ids;

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

    public Integer[] getIds() {
        return ids;
    }

    public void setIds(Integer[] ids) {
        this.ids = ids;
    }
}