package com.alivecaren.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 切面
 */
@Component
@Aspect
@Order(-100)
public class DataSourceSwitchAspect {

    @Pointcut("execution(* com.alivecaren.service.impl..*.*(..))")
    private void dbAspect() {
    }

    @Before("dbAspect()")
    public void ChangeDataSource(JoinPoint joinPoint){
        Class<?> className = joinPoint.getTarget().getClass();
        //获得访问的方法名
        String methodName = joinPoint.getSignature().getName();
        //得到方法的参数的类型
        Class[] argClass = ((MethodSignature)joinPoint.getSignature()).getParameterTypes();
        try {
            Method method = className.getMethod(methodName, argClass);
            //注解在class上
            if(className.isAnnotationPresent(DB.class)){
                DB annotation = className.getAnnotation(DB.class);
                DbContextHolder.setDbKey(annotation.value());
            }
            //注解在method上
            if(method.isAnnotationPresent(DB.class)){
                DB annotation = method.getAnnotation(DB.class);
                DbContextHolder.setDbKey(annotation.value());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
