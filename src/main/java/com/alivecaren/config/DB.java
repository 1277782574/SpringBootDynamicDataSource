package com.alivecaren.config;

import com.alivecaren.enums.DBTypeEnum;

import java.lang.annotation.*;

@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DB {
    DBTypeEnum value() default DBTypeEnum.CLOUD_DB;
}
