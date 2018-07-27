package com.example.SpringBootRedis.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhongweichang
 * @email 15090552277@163.com
 * @date 2018/7/26 下午1:54
 */
@Data
public class PersonBean implements Serializable{

    private Long id;

    private String name;

    private Integer age;

    private String address;

    private Date gmtCreate;

    private Date gmtModified;

}
