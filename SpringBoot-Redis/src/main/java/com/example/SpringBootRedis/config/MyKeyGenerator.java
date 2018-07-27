package com.example.SpringBootRedis.config;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author zhongweichang
 * @email 15090552277@163.com
 * @date 2018/7/27 下午5:27
 */
// 自定义缓存key的生成类实现如下：
@Component
public class MyKeyGenerator implements KeyGenerator {

    @Override
    public Object generate(Object target, Method method, Object... params) {
        // TODO Auto-generated method stub
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("target", target.getClass());//放入target的名字
//        map.put("method", method.getName());//放入method的名字
//        if (params != null && params.length > 0) {//把所有参数放进去
//            int i = 0;
//            for (Object o : params) {
//                map.put("params-" + i, o);
//                i++;
//            }
//        }

        String key = target.getClass().toGenericString();
        key = key + "." + method.getName();

//        String str = JSONObject.toJSON(map).toString();
        System.out.println("自定义缓存，使用第一参数作为缓存key = " + key);
        // 仅仅用于测试，实际不可能这么写
        return key;
    }

}