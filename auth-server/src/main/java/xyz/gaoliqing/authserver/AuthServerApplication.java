package xyz.gaoliqing.authserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 *  1. 开启基于注解的缓存   @EnableCaching
 *  2. 标注缓存注解   @Cacheable  根据方法的请求参数对其结果进行缓存
 *                   @CacheEvict    清空缓存
 *                   @CachePut      保证方法被调用, 又希望结果被缓存
 */

@SpringBootApplication
@EnableCaching
public class AuthServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthServerApplication.class, args);
    }

}