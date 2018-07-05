package cn.tpson.kulu.gas.constant;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * Created by Zhangka in 2018/06/20
 */
public enum EqpStatusEnum {
    STATUS_INSTALL_NORMAL((short)0, "正常"),
    STATUS_INSTALL_WAITING((short)1, "装机待审核"),
    STATUS_INSTALL_REFUSE((short)2, "装机已拒绝"),
    STATUS_INSTALL_CANCEL((short)3, "装机已取消"),
    STATUS_UNINSTALL_WAITING((short)4, "拆机待审核"),
    STATUS_UNINSTALL_REFUSE((short)5, "拆机已拒绝"),
    STATUS_UNINSTALL_CANCEL((short)6, "拆机已取消"),
    STATUS_UNINSTALL_NORMAL((short)7, "拆机已审核");

    EqpStatusEnum(Short type, String name) {
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
        if (Objects.equals(STATUS_INSTALL_NORMAL.type, type)) {
            return STATUS_INSTALL_NORMAL.name;
        } else if (Objects.equals(STATUS_INSTALL_WAITING.type, type)) {
            return STATUS_INSTALL_WAITING.name;
        } else if (Objects.equals(STATUS_INSTALL_REFUSE.type, type)) {
            return STATUS_INSTALL_REFUSE.name;
        } else if (Objects.equals(STATUS_INSTALL_CANCEL.type, type)) {
            return STATUS_INSTALL_CANCEL.name;
        } else if (Objects.equals(STATUS_UNINSTALL_WAITING.type, type)) {
            return STATUS_UNINSTALL_WAITING.name;
        } else if (Objects.equals(STATUS_UNINSTALL_REFUSE.type, type)) {
            return STATUS_UNINSTALL_REFUSE.name;
        } else if (Objects.equals(STATUS_UNINSTALL_CANCEL.type, type)) {
            return STATUS_UNINSTALL_CANCEL.name;
        } else if (Objects.equals(STATUS_UNINSTALL_NORMAL.type, type)) {
            return STATUS_UNINSTALL_NORMAL.name;
        } else {
            return null;
        }
    }

    public static Short typeOf(String name) {
        if (StringUtils.isBlank(name)) {
            return null;
        }

        if (STATUS_INSTALL_NORMAL.name.contains(name)) {
            return STATUS_INSTALL_NORMAL.type;
        } else if (STATUS_INSTALL_WAITING.name.contains(name)) {
            return STATUS_INSTALL_WAITING.type;
        } else if (STATUS_INSTALL_REFUSE.name.contains(name)) {
            return STATUS_INSTALL_REFUSE.type;
        } else if (STATUS_INSTALL_CANCEL.name.contains(name)) {
            return STATUS_INSTALL_CANCEL.type;
        } else if (STATUS_UNINSTALL_WAITING.name.contains(name)) {
            return STATUS_UNINSTALL_WAITING.type;
        } else if (STATUS_UNINSTALL_REFUSE.name.contains(name)) {
            return STATUS_UNINSTALL_REFUSE.type;
        } else if (STATUS_UNINSTALL_CANCEL.name.contains(name)) {
            return STATUS_UNINSTALL_CANCEL.type;
        } else if (STATUS_UNINSTALL_NORMAL.name.contains(name)) {
            return STATUS_UNINSTALL_NORMAL.type;
        } else {
            return null;
        }
    }
}
