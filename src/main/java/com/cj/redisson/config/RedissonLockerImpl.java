//package com.cj.redisson.config;
//
//import org.redisson.api.RLock;
//import org.redisson.api.RedissonClient;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Component;
//
//import java.util.concurrent.TimeUnit;
//
///**
// * @Description:
// * @Author: CJ
// * @Data: 2021/4/7 10:41
// */
//@Component
//public class RedissonLockerImpl implements RedissonLocker {
//
//    @Autowired
//    private RedissonClient redissonClient;
//
//    @Autowired
//    private RedisTemplate<Object, Object> redisTemplate;
//
//
//    /**
//     * key 值是否存在
//     *
//     * @param key
//     * @return
//     */
//    public boolean existKey(String key) {
//        return redisTemplate.hasKey(key);
//    }
//
//    /************************** 可重入锁 **************************/
//
//    /**
//     * 拿不到lock就不罢休，不然线程就一直block 没有超时时间,默认30s
//     *
//     * @param lockKey
//     * @return
//     */
//    @Override
//    public RLock lock(String lockKey) {
//        RLock lock = redissonClient.getLock(lockKey);
//        lock.lock();
//        return lock;
//    }
//
//    /**
//     * 自己设置超时时间
//     *
//     * @param lockKey 锁的key
//     * @param timeout 秒 如果是-1，直到自己解锁，否则不会自动解锁
//     * @return
//     */
//    @Override
//    public RLock lock(String lockKey, int timeout) {
//        RLock lock = redissonClient.getLock(lockKey);
//        lock.lock(timeout, TimeUnit.SECONDS);
//        return lock;
//    }
//
//    /**
//     * 自己设置超时时间
//     *
//     * @param lockKey 锁的key
//     * @param unit    锁时间单位
//     * @param timeout 超时时间
//     *
//     */
//    @Override
//    public RLock lock(String lockKey, TimeUnit unit, int timeout) {
//        RLock lock = redissonClient.getLock(lockKey);
//        lock.lock(timeout, unit);
//        return lock;
//    }
//
//    /**
//     * 尝试加锁，最多等待waitTime，上锁以后leaseTime自动解锁
//     *
//     * @param lockKey   锁key
//     * @param unit      锁时间单位
//     * @param waitTime  等到最大时间，强制获取锁
//     * @param leaseTime 锁失效时间
//     * @return 如果获取成功，则返回true，如果获取失败（即锁已被其他线程获取），则返回false
//     */
//    @Override
//    public boolean tryLock(String lockKey, TimeUnit unit, LockConstant lockTime) {
//        RLock lock = redissonClient.getLock(lockKey);
//        try {
//            boolean existKey =  existKey(lockKey);
//            if (existKey) {// 已经存在了，就直接返回
//                return false;
//            }
//            return lock.tryLock(lockTime.getWaitTime(), lockTime.getLeaseTime(), unit);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    /************************** 公平锁 **************************/
//    /**
//     * 尝试加锁，最多等待waitTime，上锁以后leaseTime自动解锁
//     *
//     * @param lockKey   锁key
//     * @param unit      锁时间单位
//     * @param waitTime  等到最大时间，强制获取锁
//     * @param leaseTime 锁失效时间
//     * @return 如果获取成功，则返回true，如果获取失败（即锁已被其他线程获取），则返回false
//     */
//    public boolean fairLock(String lockKey, TimeUnit unit, LockConstant lockTime) {
//        RLock fairLock = redissonClient.getFairLock(lockKey);
//        try {
//            boolean existKey =  existKey(lockKey);
//            if (existKey) {// 已经存在了，就直接返回
//                return false;
//            }
//            return fairLock.tryLock(lockTime.getWaitTime(), lockTime.getLeaseTime(), unit);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    /**
//     * 尝试加锁，最多等待waitTime，上锁以后leaseTime自动解锁
//     *
//     * @param lockKey   锁key
//     * @param unit      锁时间单位
//     * @param waitTime  等到最大时间，强制获取锁，默认是三秒钟
//     * @param leaseTime 锁失效时间
//     * @return 如果获取成功，则返回true，如果获取失败（即锁已被其他线程获取），则返回false
//     */
//    public boolean fairLock(String lockKey, TimeUnit unit, int leaseTime) {
//        RLock fairLock = redissonClient.getFairLock(lockKey);
//        try {
//            boolean existKey =  existKey(lockKey);
//            if (existKey) {// 已经存在了，就直接返回
//                return false;
//            }
//            return fairLock.tryLock(3, leaseTime, unit);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    /**
//     * 释放锁
//     *
//     * @param lockKey 锁key
//     */
//    @Override
//    public void unlock(String lockKey) {
//        try {
//            RLock lock = redissonClient.getLock(lockKey);
//            lock.unlock();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 释放锁
//     */
//    @Override
//    public void unlock(RLock lock) {
//        try {
//            lock.unlock();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//}