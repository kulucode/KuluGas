package cn.tpson.kulu.gas.domain;

import java.util.Date;

public class SysUserBuildingSiteDO extends BaseDO {
    private Integer uid;

    private String name;

    private String projName;

    private String pm;

    private String projAddr;

    private String contactPerson;

    private String phoneNo;

    private String licenceNo;

    private String licencePic;

    private String busiLicencePic;


    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProjName() {
        return projName;
    }

    public void setProjName(String projName) {
        this.projName = projName;
    }

    public String getPm() {
        return pm;
    }

    public void setPm(String pm) {
        this.pm = pm;
    }

    public String getProjAddr() {
        return projAddr;
    }

    public void setProjAddr(String projAddr) {
        this.projAddr = projAddr;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getLicenceNo() {
        return licenceNo;
    }

    public void setLicenceNo(String licenceNo) {
        this.licenceNo = licenceNo;
    }

    public String getLicencePic() {
        return licencePic;
    }

    public void setLicencePic(String licencePic) {
        this.licencePic = licencePic;
    }

    public String getBusiLicencePic() {
        return busiLicencePic;
    }

    public void setBusiLicencePic(String busiLicencePic) {
        this.busiLicencePic = busiLicencePic;
    }
}