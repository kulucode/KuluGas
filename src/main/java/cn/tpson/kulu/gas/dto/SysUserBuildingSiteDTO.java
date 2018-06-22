package cn.tpson.kulu.gas.dto;

import javax.validation.constraints.NotBlank;
import java.time.Instant;

public class SysUserBuildingSiteDTO extends BaseDTO {
    private Integer uid;

    @NotBlank(message = "施工单位名称不能为空.")
    private String name;

    @NotBlank(message = "项目名称不能为空.")
    private String projName;

    @NotBlank(message = "项目经理不能为空.")
    private String pm;

    @NotBlank(message = "项目地址不能为空.")
    private String projAddr;

    @NotBlank(message = "联系人不能为空.")
    private String contactPerson;

    @NotBlank(message = "联系电话不能为空.")
    private String phoneNo;

    private String licenceNo;

    private String licencePic;

    private String busiLicencePic;

    /**
     * 定位信息，例：{"addr":"xxx","lon":"xxx","lat":"xxx"}
     */
    private String location;

    /**
     * 税号.
     */
    private String taxId;

    //////////////////////////////////////////////////////////////////////
    private LocationDTO gps;

    public SysUserBuildingSiteDTO() {}

    public SysUserBuildingSiteDTO(Integer id, Boolean deleted, Instant gmtCreate, Instant gmtModified) {
        super(id, deleted, gmtCreate, gmtModified);
    }

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public LocationDTO getGps() {
        return gps;
    }

    public void setGps(LocationDTO gps) {
        this.gps = gps;
    }
}