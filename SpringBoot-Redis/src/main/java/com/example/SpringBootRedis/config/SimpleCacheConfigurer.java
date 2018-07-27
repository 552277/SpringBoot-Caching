package com.example.SpringBootRedis.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

/**
 * @author zhongweichang
 * @email 15090552277@163.com
 * @date 2018/7/26 下午8:28
 */
@Configuration
@EnableCaching//我们开启缓存了
public class SimpleCacheConfigurer {

    @Bean
    public SimpleCacheManager simpleCacheManager() {//springboot支持的缓存有很多，我们选择一个最为简单的
        SimpleCacheManager s = new SimpleCacheManager();
        s.setCaches(Collections.singletonList(new ConcurrentMapCache("people")));
        return s;
    }

    @Bean
    public KeyGenerator cacheKeyGenerator() {//缓存key生成者
        CacheKeyGenerator cacheKeyGenerator = new CacheKeyGenerator();
        return cacheKeyGenerator;

    }
}

