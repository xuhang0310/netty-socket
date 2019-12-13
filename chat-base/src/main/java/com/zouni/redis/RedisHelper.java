package com.zouni.redis;

import com.zouni.netty.util.SpringApplicationContextUtil;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class RedisHelper {


    private static RedisHelper instance = new RedisHelper();
    private static final String Valid_Suffix = "_valid";
    public static final long Default_Expire = 600; //默认过期时间 单位时间秒
    private volatile RedisTemplate redisTemplate = null;

    public static RedisHelper Instance(){
        if(instance.redisTemplate == null){
            instance.InitRedisTemplate();
        }
        return instance;
    }

    private synchronized void InitRedisTemplate(){
        if(redisTemplate == null) {
            if (SpringApplicationContextUtil.getApplicationContext() == null) {
                if (SpringApplicationContextUtil.getApplicationContext() != null) {
                    instance.redisTemplate = (RedisTemplate) SpringApplicationContextUtil.getApplicationContext().getBean("redisTemplate");
                }
            } else {
                instance.redisTemplate = (RedisTemplate)SpringApplicationContextUtil.getApplicationContext().getBean("redisTemplate");
            }
        }
    }

    public boolean UpdateKeyValidAndPutKV(String key, Object value){
        redisTemplate.opsForValue().set(key, value, Default_Expire, TimeUnit.SECONDS);
        SetKeyValid(key, true);
        return true;
    }
    public void deleteKey(String key){
        Set<String> keys =redisTemplate.keys(key+"*");
        redisTemplate.delete(keys);
    }
    public boolean UpdateKeyValidAndPutKV(String key, Object value, long expire){
        redisTemplate.opsForValue().set(key, value, expire, TimeUnit.SECONDS);
        SetKeyValid(key, true);
        return true;
    }
    public boolean UpdateKeyValidAndPutKV(String key, Object value, long expire, TimeUnit timeUnit){
        redisTemplate.opsForValue().set(key, value, expire, timeUnit);
        SetKeyValid(key, true, expire, timeUnit);
        return true;
    }
    public boolean IsDataValid(String key){
        Long value = redisTemplate.opsForValue().increment(key + Valid_Suffix, 0L);
        return value > 1;
    }
    public Object GetDataByKey(String key){
        return redisTemplate.opsForValue().get(key);
    }
    public Object GetDataByKey(String key, String value){
        Object object= redisTemplate.opsForValue().get(key);
        if(object==null){
            object=value;
        }
        return object;
    }

    public void SetDataByKey(String key, Object value) { redisTemplate.opsForValue().set(key, value);}
    public void SetDataByKey(String key, Object value,long expire, TimeUnit timeUnit) { redisTemplate.opsForValue().set(key, value, expire, timeUnit);}

    public List<Object> GetHashSetValuesByKey(String key){
        List<Object> list = redisTemplate.opsForHash().values(key);
        return list;
    }
    public List<Object> GetListByKey(String key){
        List<Object> list = redisTemplate.opsForList().range(key, 0, -1);
        return list;
    }

    public long GetListSizeByKey(String key){
        return redisTemplate.opsForList().size(key);
    }
    public List<Object> GetListByKey(String key, int start, int end){
        List<Object> list = redisTemplate.opsForList().range(key, start, end);
        return list;
    }

    public Object GetHashSetByKey(String key, Object hk){
        Object obj = redisTemplate.opsForHash().get(key,hk);
        return obj;
    }

    public Map<Object, Object> GetHashMapByKey(String key){
        Map<Object, Object> map = redisTemplate.opsForHash().entries(key);
        return map;
    }

    public boolean UpdateKeyValidAndPutHash(String key, Map<Object, Object> map) {
        //redisTemplate.opsForHash().delete();
        redisTemplate.delete(key);
        redisTemplate.opsForHash().putAll(key, map);
        redisTemplate.expire(key, Default_Expire, TimeUnit.SECONDS);
        SetKeyValid(key, true);
        return true;
    }

    public boolean UpdateKeyValidAndPutHashWithoutExpire(String key, Map<Object, Object> map) {
        //redisTemplate.opsForHash().delete();
        redisTemplate.delete(key);
        redisTemplate.opsForHash().putAll(key, map);
        SetKeyValidWithOutExpire(key, true);
        return true;
    }

    public void SetKeyValidWithOutExpire(String key, boolean b) {
        if(b){
            redisTemplate.opsForValue().increment(key + Valid_Suffix, 1L);
        }
        else{
            redisTemplate.delete(key + Valid_Suffix);
        }
    }

    public boolean PutHashMap(String key, Map<Object, Object> map) {
        //redisTemplate.opsForHash().delete();
        redisTemplate.delete(key);
        redisTemplate.opsForHash().putAll(key, map);
        redisTemplate.expire(key, Default_Expire, TimeUnit.SECONDS);
        return true;
    }
    public boolean PutHashMapWithOutExpire(String key, Map<Object, Object> map) {
        //redisTemplate.opsForHash().delete();
        redisTemplate.delete(key);
        redisTemplate.opsForHash().putAll(key, map);
        return true;
    }
    public boolean UpdateKeyValidAndPutList(String key, List<Object> list) {
        //redisTemplate.opsForHash().delete();
        Long genNum=redisTemplate.opsForValue().increment( key+"UpdateKeyValidAndPutList",1 );
        redisTemplate.expire( key+"UpdateKeyValidAndPutList",10,TimeUnit.MILLISECONDS );
        if(genNum==1){
            redisTemplate.delete(key);
            if(list.size() > 0){
                redisTemplate.opsForList().rightPushAll(key, list);
                redisTemplate.expire(key, Default_Expire, TimeUnit.SECONDS);
                SetKeyValid(key, true);
            }
        }

        return true;
    }

    public boolean SetList(String key, List<Object> list) {
        //redisTemplate.opsForHash().delete();
        Long genNum=redisTemplate.opsForValue().increment( key+"setList",1 );
        redisTemplate.expire( key+"setList",10,TimeUnit.MILLISECONDS );
        if(genNum==1){
            redisTemplate.delete(key);
            if(list.size() > 0){
                redisTemplate.opsForList().rightPushAll(key, list);
                redisTemplate.expire(key, Default_Expire, TimeUnit.SECONDS);
            }
        }
        return true;
    }

    public boolean AppendList(String key, List<Object> list){
        if(list.size() > 0){
            redisTemplate.opsForList().rightPushAll(key, list);
            redisTemplate.expire(key, Default_Expire, TimeUnit.SECONDS);
        }
        return true;
    }

    public boolean SetListWithoutExpire(String key, List<Object> list) {
        //redisTemplate.opsForHash().delete();
        redisTemplate.delete(key);
        if(list.size() > 0){
            redisTemplate.opsForList().rightPushAll(key, list);
        }
        return true;
    }

    public void SetKeyValid(String key, boolean b) {
        if(b){
            //redisTemplate.opsForValue().set(key + Valid_Suffix, 0L, Default_Expire, TimeUnit.SECONDS);
            redisTemplate.opsForValue().increment(key + Valid_Suffix, 1L);
            redisTemplate.expire(key + Valid_Suffix, Default_Expire, TimeUnit.SECONDS);
        }
        else{
            redisTemplate.delete(key + Valid_Suffix);
        }

    }
    public void delete(String key){
        redisTemplate.delete(key );

    }

    public void SetKeyValid(String key, boolean b, long expire, TimeUnit timeUnit) {
        if(b){
            //redisTemplate.opsForValue().set(key + Valid_Suffix, 0L, Default_Expire, TimeUnit.SECONDS);
            redisTemplate.opsForValue().increment(key + Valid_Suffix, 1L);
            redisTemplate.expire(key + Valid_Suffix, expire, timeUnit);
        }
        else{
            redisTemplate.delete(key + Valid_Suffix);
        }

    }

    public boolean AddHashValue(String key, Object hk, Object object){
        redisTemplate.opsForHash().put(key, hk, object);
        redisTemplate.expire(key, Default_Expire, TimeUnit.SECONDS);
        return true;
    }
    public boolean AddHashValueWithoutExpire(String key, Object hk, Object object){
        redisTemplate.opsForHash().put(key, hk, object);
        return true;
    }

    public void AddListToLeft(String key, Object obj) {
        redisTemplate.opsForList().leftPush(key, obj);
    }

    public List<Object> GetListByRange(String key, int range) {
        return redisTemplate.opsForList().range(key, 0, range);
    }

    public void SetKeyExpireTime(String key, long time, TimeUnit unit) {
        if(time > 0)
            redisTemplate.expire(key, time, unit);
    }
    public boolean PushList(String key, Object value) {
        this.redisTemplate.opsForList().rightPush(key, value);
        return true;
    }

    public boolean PushListWithoutExpire(String key, Object value) {
        this.redisTemplate.opsForList().rightPush(key, value);
        return true;
    }

    public Object LeftPopList(String key) {
        Object object = this.redisTemplate.opsForList().leftPop(key);
        return object;
    }

    public void Reconnect() {
        redisTemplate = null;
    }
}
