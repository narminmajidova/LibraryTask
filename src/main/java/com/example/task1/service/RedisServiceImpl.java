package com.example.task1.service;

import com.example.task1.model.RedisEntity;
import com.example.task1.repository.RedisEntityRepository;
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