package com.alivecaren.config;

import com.alivecaren.enums.DBTypeEnum;

public class DbContextHolder {
    private static final ThreadLocal dbKey=new ThreadLocal<>();

    public static void setDbKey(DBTypeEnum dbTypeEnum){
        dbKey.set(dbTypeEnum.getValue());
    }

    public static String getDbKey(){
        return  (String)dbKey.get();
    }

    public static void cleanDbKey(){
        dbKey.remove();
    }
}
