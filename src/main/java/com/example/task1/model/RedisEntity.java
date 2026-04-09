package com.example.task1.model;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

@Data
@RedisHash(value = "redisEntity", timeToLive = 3600)
public class RedisEntity {
    private Long id;
    private String text;

}
