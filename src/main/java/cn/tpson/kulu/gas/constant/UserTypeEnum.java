package cn.tpson.kulu.gas.constant;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * Created by Zhangka in 2018/06/20
 */
public enum UserTypeEnum {
    USER_SYS((short)0, "系统管理员"),
    USER_GOV((short)1, "政府"),
    USER_SER((short)2, "服务公司"),
    USER_BUI((short)3, "施工单位"),
    USER_PER((short)4, "个人机主"),
    USER_PRO((short)5, "工程安装");

    UserTypeEnum(Short type, String name) {
        this.type = type;
        this.name = name;
    }

    private Short type;
    private String name;

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String nameOf(Short type) {
        if (Objects.equals(USER_SYS.type, type)) {
            return USER_SYS.name;
        } else if (Objects.equals(USER_GOV.type, type)) {
            return USER_GOV.name;
        } else if (Objects.equals(USER_SER.type, type)) {
            return USER_SER.name;
        } else if (Objects.equals(USER_BUI.type, type)) {
            return USER_BUI.name;
        } else if (Objects.equals(USER_PER.type, type)) {
            return USER_PER.name;
        } else if (Objects.equals(USER_PRO.type, type)) {
            return USER_PRO.name;
        } else {
            return null;
        }
    }

    public static Short typeOf(String name) {
        if (StringUtils.isBlank(name)) {
            return null;
        }

        if (USER_SYS.name.contains(name)) {
            return USER_SYS.type;
        } else if (USER_GOV.name.contains(name)) {
            return USER_GOV.type;
        } else if (USER_SER.name.contains(name)) {
            return USER_SER.type;
        } else if (USER_BUI.name.contains(name)) {
            return USER_BUI.type;
        } else if (USER_PER.name.contains(name)) {
            return USER_PER.type;
        } else if (USER_PRO.name.contains(name)) {
            return USER_PRO.type;
        } else {
            return null;
        }
    }
}
