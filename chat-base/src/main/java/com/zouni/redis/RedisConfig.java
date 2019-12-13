package com.zouni.redis;


import com.zouni.kryo.KryoRedisSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory factory){
        RedisTemplate<Object, Object> template = new RedisTemplate<Object, Object>();
        template.setConnectionFactory(factory);
        KryoRedisSerializer redisSerializer=new KryoRedisSerializer(Object.class);
        template.setValueSerializer( redisSerializer );
        template.setKeySerializer(new StringRedisSerializer());
        return template;
    };
}
