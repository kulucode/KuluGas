package cn.tpson.kulu.gas.dto;

import java.util.List;

public class SysRoleDTO extends BaseDTO {
    private String roleName;

    private List<SysMenuDTO> sysMenus;

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