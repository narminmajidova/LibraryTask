package com.user.service.controller;

import com.user.service.model.RedisEntity;
import com.user.service.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/redis")
@RequiredArgsConstructor
public class RedisController {

    private final RedisService redisService;

    @PostMapping("/save")
    public RedisEntity save(@RequestBody RedisEntity redisEntity){
        return redisService.save(redisEntity);
    }

    @GetMapping("/redis/{id}")
    public RedisEntity save(@PathVariable Long id){
        return redisService.getById(id);
    }

    @DeleteMapping("/redis/{id}")
    public void delete(@PathVariable Long id){
       redisService.deleteById(id);
    }

}
