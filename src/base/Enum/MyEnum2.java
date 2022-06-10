package base.Enum;

/**
 * @Author Yanghz
 * @Since 2022/4/21
 * @Description
 */
public enum MyEnum2 {

    RCA001_001("RCA", "001", "RCA001", "单位职工参保"),
    ;

    private final String code;    //业务编码
    private final String serial;  //业务编号
    private final String value;   //业务值
    private final String desc;    //业务描述

    MyEnum2(String code, String serial, String value, String desc) {
        this.code = code;
        this.serial = serial;
        this.value = value;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getSerial() {
        return serial;
    }

    public String getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    public static MyEnum2 getMyEnum2(String code, String serial) {
        MyEnum2[] values = MyEnum2.values();
        for (MyEnum2 enum2 : values) {
            if (enum2.code.equals(code) && enum2.serial.equals(serial)) {
                return enum2;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return super.toString();
//        return this.value + "(" + this.desc + ")";
    }
}
