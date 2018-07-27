package com.example.SpringBootRedis.controller;

import com.example.SpringBootRedis.bean.PersonBean;
import com.example.SpringBootRedis.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author zhongweichang
 * @email 15090552277@163.com
 * @date 2018/7/26 下午2:37
 */
@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @RequestMapping(method = RequestMethod.POST)
    public PersonBean put(HttpServletRequest request, @RequestBody PersonBean personBean) {
        return personService.save(personBean);
    }

    @RequestMapping(method = RequestMethod.PATCH)
    public PersonBean getPerson(HttpServletRequest request, @RequestBody PersonBean personBean) {
        return personService.findById(personBean);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public boolean deletePerson(HttpServletRequest request, @PathVariable("id") long id) {
        return personService.delete(id);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<PersonBean> listAllPerson(HttpServletRequest request) {
        return personService.listAllPerson();
    }

    @RequestMapping(value = "/list/{id}", method = RequestMethod.DELETE)
    public boolean deleteOneOfList(HttpServletRequest request, @PathVariable("id") long id) {
        return personService.deleteOneOfList(id);
    }
}
