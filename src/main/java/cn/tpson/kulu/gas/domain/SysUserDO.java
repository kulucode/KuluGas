package cn.tpson.kulu.gas.domain;

public class SysUserDO extends BaseDO {
    private String username;

    private String nickname;

    private String realname;

    private String password;

    private Short status;

    /**
     * 0:系统管理员;1:政府;2:服务公司;3:施工单位;4:个人机主;5:工程安装;
     */
    private Short type;

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