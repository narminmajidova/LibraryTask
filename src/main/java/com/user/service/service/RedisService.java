package com.user.service.service;

import com.user.service.model.RedisEntity;

public interface RedisService {

    RedisEntity save(RedisEntity redisEntity);
    RedisEntity getById(Long id);
    void deleteById(Long id);
}