package com.utkarshprojects.salestrackingtool.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfig {

    @Value("${redis.host}")
    private String redisHost;

    @Value("${redis.port}")
    private int redisPort;

    @Value("${redis.password}")
    private String redisPassword;

    private static final Logger logger = LogManager.getLogger(RedisConfig.class);

    @Bean
    public JedisPool jedisPool() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(10); // Set your desired max connections
        poolConfig.setMinIdle(2);   // Set your desired minimum idle connections
        return new JedisPool(poolConfig, redisHost, redisPort, 5000, redisPassword);
    }
}
