package com.cj.redisson.controller;

import com.cj.redisson.service.RedissonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description:
 * @Author: CJ
 * @Data: 2021/4/7 12:49
 */
@RestController
public class RedissonController {

    @Resource
    private RedissonService redissonService;

    @GetMapping("/cashier")
    public void cashier(){
        redissonService.cashier();
    }

    @GetMapping("/submitOrder")
    public void submitOrder(){
        redissonService.submitOrder();
    }

}
