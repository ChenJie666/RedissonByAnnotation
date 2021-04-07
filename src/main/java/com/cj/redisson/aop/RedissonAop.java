package com.cj.redisson.aop;

import com.cj.redisson.annotation.RlockRepeatSubmit;
import com.cj.redisson.constant.LockConstant;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: CJ
 * @Data: 2021/4/7 12:57
 */
@Configuration
@Aspect
public class RedissonAop {

    @Resource
    private Redisson redisson;

    @Pointcut("@annotation(com.cj.redisson.annotation.RlockRepeatSubmit)")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object redissonLock(ProceedingJoinPoint pjp) {
        // 获取方法的注解
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        RlockRepeatSubmit annotation = method.getAnnotation(RlockRepeatSubmit.class);
        LockConstant lockConstant = annotation.lockConstant();
        Assert.notNull(lockConstant, "服务器异常");

        // 获取方法的参数
        Object[] args = pjp.getArgs();

        String keyPrefix = lockConstant.getKeyPrefix();
        int leaseTime = lockConstant.getLeaseTime();
        int waitTime = lockConstant.getWaitTime();
        TimeUnit timeUnit = lockConstant.getTimeUnit();
        String message = lockConstant.getMessage();

        RLock lock = redisson.getLock(keyPrefix);
        try {
            boolean ifLock = lock.tryLock(waitTime, leaseTime, timeUnit);
            if (ifLock) {
                System.out.println("上锁");
                return pjp.proceed();
            } else {
                throw new IllegalStateException(message);
            }
        } catch (Throwable throwable) {
            throw new IllegalStateException("服务器aop代理异常");
        } finally {
            System.out.println("解锁");
            lock.unlock();
        }
    }

}
