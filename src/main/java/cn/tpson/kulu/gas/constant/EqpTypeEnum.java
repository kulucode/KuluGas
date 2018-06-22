package cn.tpson.kulu.gas.constant;

import java.util.Objects;

/**
 * Created by Zhangka in 2018/06/20
 */
public enum EqpTypeEnum {
    EQP_TYPE_1((short)1, "挖掘机"),
    EQP_TYPE_2((short)2, "装载机"),
    EQP_TYPE_3((short)3, "挖掘装载机"),
    EQP_TYPE_4((short)4, "叉车"),
    EQP_TYPE_5((short)5, "推土机"),
    EQP_TYPE_6((short)6, "压路机"),
    EQP_TYPE_7((short)7, "摊铺机"),
    EQP_TYPE_8((short)8, "铣刨机"),
    EQP_TYPE_9((short)9, "旋挖钻机"),
    EQP_TYPE_10((short)10, "打桩机"),
    EQP_TYPE_11((short)11, "混泥土输送泵"),
    EQP_TYPE_12((short)12, "强夯机"),
    EQP_TYPE_13((short)13, "履带式吊车"),
    EQP_TYPE_14((short)14, "发电机组"),
    EQP_TYPE_99((short)99, "其他");

    EqpTypeEnum(Short type, String name) {
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
        if (Objects.equals(EQP_TYPE_1.type, type)) {
            return EQP_TYPE_1.name;
        } else if (Objects.equals(EQP_TYPE_2.type, type)) {
            return EQP_TYPE_2.name;
        } else if (Objects.equals(EQP_TYPE_3.type, type)) {
            return EQP_TYPE_3.name;
        } else if (Objects.equals(EQP_TYPE_4.type, type)) {
            return EQP_TYPE_4.name;
        } else if (Objects.equals(EQP_TYPE_5.type, type)) {
            return EQP_TYPE_5.name;
        } else if (Objects.equals(EQP_TYPE_6.type, type)) {
            return EQP_TYPE_6.name;
        } else if (Objects.equals(EQP_TYPE_7.type, type)) {
            return EQP_TYPE_7.name;
        } else if (Objects.equals(EQP_TYPE_8.type, type)) {
            return EQP_TYPE_8.name;
        } else if (Objects.equals(EQP_TYPE_9.type, type)) {
            return EQP_TYPE_9.name;
        } else if (Objects.equals(EQP_TYPE_10.type, type)) {
            return EQP_TYPE_10.name;
        } else if (Objects.equals(EQP_TYPE_11.type, type)) {
            return EQP_TYPE_11.name;
        } else if (Objects.equals(EQP_TYPE_12.type, type)) {
            return EQP_TYPE_12.name;
        } else if (Objects.equals(EQP_TYPE_13.type, type)) {
            return EQP_TYPE_13.name;
        } else if (Objects.equals(EQP_TYPE_14.type, type)) {
            return EQP_TYPE_14.name;
        } else if (Objects.equals(EQP_TYPE_99.type, type)) {
            return EQP_TYPE_99.name;
        } else {
            return null;
        }
    }

    public static Short typeOf(String name) {
        if (EQP_TYPE_1.name.contains(name)) {
            return EQP_TYPE_1.type;
        } else if (EQP_TYPE_2.name.contains(name)) {
            return EQP_TYPE_2.type;
        } else if (EQP_TYPE_3.name.contains(name)) {
            return EQP_TYPE_3.type;
        } else if (EQP_TYPE_4.name.contains(name)) {
            return EQP_TYPE_4.type;
        } else if (EQP_TYPE_5.name.contains(name)) {
            return EQP_TYPE_5.type;
        } else if (EQP_TYPE_6.name.contains(name)) {
            return EQP_TYPE_6.type;
        } else if (EQP_TYPE_7.name.contains(name)) {
            return EQP_TYPE_7.type;
        } else if (EQP_TYPE_8.name.contains(name)) {
            return EQP_TYPE_8.type;
        } else if (EQP_TYPE_9.name.contains(name)) {
            return EQP_TYPE_9.type;
        } else if (EQP_TYPE_10.name.contains(name)) {
            return EQP_TYPE_10.type;
        } else if (EQP_TYPE_11.name.contains(name)) {
            return EQP_TYPE_11.type;
        } else if (EQP_TYPE_12.name.contains(name)) {
            return EQP_TYPE_12.type;
        } else if (EQP_TYPE_13.name.contains(name)) {
            return EQP_TYPE_13.type;
        } else if (EQP_TYPE_14.name.contains(name)) {
            return EQP_TYPE_14.type;
        } else if (EQP_TYPE_99.name.contains(name)) {
            return EQP_TYPE_99.type;
        } else {
            return null;
        }
    }
}
