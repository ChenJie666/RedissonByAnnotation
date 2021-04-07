package com.cj.redisson.annotation;

import com.cj.redisson.constant.LockConstant;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description:
 * @Author: CJ
 * @Data: 2021/4/7 10:38
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RlockRepeatSubmit {

    /**
     * 通过反射来判断使用的场景
     * @return
     */
    LockConstant lockConstant() default LockConstant.COMMON_LOCK;

}
