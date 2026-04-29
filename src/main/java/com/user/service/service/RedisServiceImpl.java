package com.user.service.service;

import com.user.service.model.RedisEntity;
import com.user.service.repository.RedisEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisServiceImpl implements RedisService {

    private final RedisEntityRepository redisEntityRepository;

    @Override
    public RedisEntity save(RedisEntity redisEntity){
        return redisEntityRepository.save(redisEntity);
    }

    @Override
    public RedisEntity getById(Long id) {
        return redisEntityRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id){
        redisEntityRepository.deleteById(id);
    }
}