package com.cj.redisson.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @Author: CJ
 * @Data: 2021/4/7 11:27
 */
@Configuration
@ConfigurationProperties(prefix = "spring.redisson")
@Data
public class RedissonProperties {

    private int timeout = 3000;

    private String host;

    private String password;

    private int database = 0;

    private int connectionPoolSize = 64;

    private int connectionMinimumIdleSize=10;

    private int slaveConnectionPoolSize = 250;

    private int masterConnectionPoolSize = 250;

    private String[] sentinelAddresses;

    private String masterName;

}
