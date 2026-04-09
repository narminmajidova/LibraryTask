package com.example.task1.service;

import com.example.task1.model.RedisEntity;

public interface RedisService {

    RedisEntity save(RedisEntity redisEntity);
    RedisEntity getById(Long id);
    void deleteById(Long id);
}