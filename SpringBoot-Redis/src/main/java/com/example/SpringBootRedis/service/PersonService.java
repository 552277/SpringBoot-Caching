package com.example.SpringBootRedis.service;


import com.example.SpringBootRedis.bean.PersonBean;
import com.example.SpringBootRedis.dao.PersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhongweichang
 * @email 15090552277@163.com
 * @date 2018/7/26 下午2:09
 */
@Service
public class PersonService {

    @Autowired
    private PersonDao personDao;
    @Autowired
    private CacheManager cacheManager;
    @Autowired
    private RedisTemplate redisTemplate;

    @CachePut(value = "people", key = "#personBean.id")
    public PersonBean save(PersonBean personBean) {

        personDao.save(personBean);
        System.out.println("1 .... 为id、key为："+ personBean.getId() + "数据做了缓存");
        return personBean;

    }

    /**
     * allEntries是boolean类型，表示是否需要清除缓存中的所有元素。默认为false，表示不需要。当指定了allEntries为true时，
     * Spring Cache将忽略指定的key。有的时候我们需要Cache一下清除所有的元素，这比一个一个清除元素更有效率。
     * @param id
     * @return
     */
    @CacheEvict(value = "people", allEntries=true)
    public boolean delete(Long id) {
        System.out.println("2 .... 删除了id、key为:" + id + "的数据缓存");
//        return personDao.delete(id);
        return false;
    }

    @Cacheable(value = "people", key = "#personBean.id")
    public PersonBean findById(PersonBean personBean) {
        PersonBean person = personDao.findById(personBean);
        if(person == null) {
            return null;
        }
        System.out.println("3 ... 为id、key为:" + person.getId() + "数据做了缓存");
        return person;
    }

    @Cacheable(value = "people", keyGenerator = "cacheKeyGenerator")
    public List<PersonBean> listAllPerson() {
        System.out.println("4 ... 已为人员列表集合数据做了缓存");
        return personDao.listAllPerson();
    }

    /**
     * 清除操作默认是在对应方法成功执行之后触发的，即方法如果因为抛出异常而未能成功返回时也不会触发清除操作。
     * 使用beforeInvocation可以改变触发清除操作的时间，当我们指定该属性值为true时，Spring会在调用该方法之前清除缓存中的指定元素。
     * @param id
     * @return
     */
    public boolean deleteOneOfList(long id) {

//        List<PersonBean> personBeans = this.listAllPerson();

//        Object listOperations = redisTemplate.opsForList().index("com.example.SpringBootRedis.service.PersonService.listAllPerson", 0);



        Cache cache = cacheManager.getCache("people");
        if(cache != null) {
            Cache.ValueWrapper valueWrapper = cache.get("com.example.SpringBootRedis.service.PersonService.listAllPerson");
            if(valueWrapper != null) {
                List<PersonBean> bizSplashScreens = (List<PersonBean>)valueWrapper.get();
            }
        }

        return false;
    }

    public void remove(String key, long count, String value)
    {
        ListOperations<String, String> oper = redisTemplate.opsForList();
        int backValue = oper.remove(key, count, value).intValue();
        System.out.println(key + " 数组长度为 : " + backValue);
    }
}
