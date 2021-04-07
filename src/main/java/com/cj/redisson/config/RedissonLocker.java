//package com.cj.redisson.config;
//
//import org.redisson.api.RLock;
//
//import java.util.concurrent.TimeUnit;
//
///**
// * @Description:
// * @Author: CJ
// * @Data: 2021/4/7 10:41
// */
//public interface RedissonLocker {
//
//    RLock lock(String lockKey);
//
//    RLock lock(String lockKey, int timeout);
//
//    RLock lock(String lockKey, TimeUnit unit, int timeout);
//
//    boolean tryLock(String lockKey, TimeUnit unit, LockConstant lockTime);
//
//    boolean fairLock(String lockKey, TimeUnit unit, LockConstant lockTime);
//
//    void unlock(String lockKey);
//
//    void unlock(RLock lock);
//
//}
