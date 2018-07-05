package cn.tpson.kulu.gas.query;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

public class SysUserQuery extends BaseQuery {
    private String username;

    private String nickname;

    private String realname;

    private String password;

    /**
     * 状态，0：正常；1：待审核；2：拒绝
     */
    private Short status;

    /**
     * 0:系统管理员;1:政府;2:服务公司;3:施工单位;4:个人机主;5:工程安装;
     */
    private Short type;

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    private Integer[] ids;

    // 搜索关键字
    private String key;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date st;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date et;

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

    public Integer[] getIds() {
        return ids;
    }

    public void setIds(Integer[] ids) {
        this.ids = ids;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Date getSt() {
        return st;
    }

    public void setSt(Date st) {
        this.st = st;
    }

    public Date getEt() {
        return et;
    }

    public void setEt(Date et) {
        this.et = et;
    }
}