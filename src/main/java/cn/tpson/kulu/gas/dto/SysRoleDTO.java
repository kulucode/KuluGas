package cn.tpson.kulu.gas.dto;

import java.time.Instant;
import java.util.List;

public class SysRoleDTO extends BaseDTO {
    private String roleName;

    private List<SysMenuDTO> sysMenus;

    public SysRoleDTO() {}

    public SysRoleDTO(Integer id, Boolean deleted, Instant gmtCreate, Instant gmtModified) {
        super(id, deleted, gmtCreate, gmtModified);
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<SysMenuDTO> getSysMenus() {
        return sysMenus;
    }

    public void setSysMenus(List<SysMenuDTO> sysMenus) {
        this.sysMenus = sysMenus;
    }
}