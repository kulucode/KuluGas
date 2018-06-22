package cn.tpson.kulu.gas.dto;

import javax.validation.constraints.NotBlank;
import java.time.Instant;

public class SysUserPersonalDTO extends BaseDTO {
    private Integer uid;

    @NotBlank(message = "身份证不能为空.")
    private String idCard;

    @NotBlank(message = "联系电话不能为空.")
    private String phoneNo;

    @NotBlank(message = "身份证正面照片不能为空.")
    private String idCardFrontPic;

    @NotBlank(message = "身份证反面照片不能为空.")
    private String idCardBackPic;

    public SysUserPersonalDTO() {}

    public SysUserPersonalDTO(Integer id, Boolean deleted, Instant gmtCreate, Instant gmtModified) {
        super(id, deleted, gmtCreate, gmtModified);
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
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

    public String getIdCardFrontPic() {
        return idCardFrontPic;
    }

    public void setIdCardFrontPic(String idCardFrontPic) {
        this.idCardFrontPic = idCardFrontPic;
    }

    public String getIdCardBackPic() {
        return idCardBackPic;
    }

    public void setIdCardBackPic(String idCardBackPic) {
        this.idCardBackPic = idCardBackPic;
    }
}