package com.alivecaren.enums;

/**
 * 数据源枚举类
 */
public enum DBTypeEnum {

    CLOUD_DB("cloud"),

    TEST_DB("test")
    ;

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


    private DBTypeEnum(String value) {
        this.value = value;
    }
}
