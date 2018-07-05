package cn.tpson.kulu.gas.dto;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.util.List;

public class SysUserDTO extends BaseDTO {
    public static final String SID = "sid";

    /**
     * 状态，0：正常；1：待审核；2：拒绝
     */
    public static final short STATUS_NORMAL = 0;
    public static final short STATUS_WAITING = 1;
    public static final short STATUS_REFUSE = 2;

    @NotBlank(message = "用户名不能为空.")
    private String username;

    private String nickname;

    //@NotBlank(message = "姓名不能为空.")
    private String realname;

    @NotBlank(message = "密码不能为空.")
    private String password;

    @NotBlank(message = "密码确认不能为空.")
    private String confirmPassword;

    /**
     * 状态，0：正常；1：待审核；2：拒绝
     */
    private Short status;

    /**
     * 0:系统管理员;1:政府;2:服务公司;3:施工单位;4:个人机主;5:工程安装;
     * @see cn.tpson.kulu.gas.constant.UserTypeEnum
     */
    private Short type;

    private SysUserBuildingSiteDTO buildingSite;

    private SysUserPersonalDTO personal;

    private BaseDTO detail;

    private List<SysRoleDTO> sysRoles;

    public SysUserDTO() {}

    public SysUserDTO(Integer id, Boolean deleted, Instant gmtCreate, Instant gmtModified) {
        super(id, deleted, gmtCreate, gmtModified);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
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

    public SysUserBuildingSiteDTO getBuildingSite() {
        return buildingSite;
    }

    public void setBuildingSite(SysUserBuildingSiteDTO buildingSite) {
        this.buildingSite = buildingSite;
    }

    public SysUserPersonalDTO getPersonal() {
        return personal;
    }

    public void setPersonal(SysUserPersonalDTO personal) {
        this.personal = personal;
    }

    public List<SysRoleDTO> getSysRoles() {
        return sysRoles;
    }

    public void setSysRoles(List<SysRoleDTO> sysRoles) {
        this.sysRoles = sysRoles;
    }

    public static void main(String[] args) {
        System.out.println(JSON.toJSONString(new SysUserDTO()));
    }

    public BaseDTO getDetail() {
        return detail;
    }

    public void setDetail(BaseDTO detail) {
        this.detail = detail;
    }
}