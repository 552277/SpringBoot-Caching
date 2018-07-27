package com.example.SpringBootRedis.dao;

import com.example.SpringBootRedis.bean.PersonBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author zhongweichang
 * @email 15090552277@163.com
 * @date 2018/7/26 下午2:09
 */
@Mapper
public interface PersonDao {

    boolean save(PersonBean personBean);

    boolean delete(Long id);

    PersonBean findById(PersonBean personBean);

    List<PersonBean> listAllPerson();
}
