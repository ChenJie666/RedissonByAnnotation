package com.cj.redisson.constant;

import lombok.Data;

import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: CJ
 * @Data: 2021/4/7 10:43
 */
public enum LockConstant {

    CASHIER("cashierLock:", 8, 10, TimeUnit.SECONDS, "请勿重复操作点击收银操作"),
    SUBMIT_ORDER("submitOrderLock", 3, 30, TimeUnit.SECONDS, "请勿重复点击下单"),
    COMMON_LOCK("commonLock:", 3, 120, TimeUnit.SECONDS, "请勿重复点击");

    private String keyPrefix;
    private int waitTime;
    private int leaseTime;
    private TimeUnit timeUnit;
    private String message;

    LockConstant(String keyPrefix, int waitTime, int leaseTime, TimeUnit timeUnit, String message) {
        this.keyPrefix = keyPrefix;
        this.waitTime = waitTime;
        this.leaseTime = leaseTime;
        this.timeUnit = timeUnit;
        this.message = message;
    }

    public String getKeyPrefix() {
        return keyPrefix;
    }

    public int getWaitTime() {
        return waitTime;
    }

    public int getLeaseTime() {
        return leaseTime;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public String getMessage() {
        return message;
    }

}
