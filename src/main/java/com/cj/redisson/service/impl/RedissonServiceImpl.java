package com.cj.redisson.service.impl;

import com.cj.redisson.constant.LockConstant;
import com.cj.redisson.annotation.RlockRepeatSubmit;
import com.cj.redisson.service.RedissonService;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: CJ
 * @Data: 2021/4/7 12:46
 */
@Service
public class RedissonServiceImpl implements RedissonService {

    @Override
    @RlockRepeatSubmit(lockConstant = LockConstant.CASHIER)
    public void cashier() {
        System.out.println("enter cashier");
        try {
            TimeUnit.SECONDS.sleep(6);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("finish cashier");
    }

    @Override
    @RlockRepeatSubmit(lockConstant = LockConstant.SUBMIT_ORDER)
    public void submitOrder() {
        System.out.println("enter submitOrder");
    }

}
