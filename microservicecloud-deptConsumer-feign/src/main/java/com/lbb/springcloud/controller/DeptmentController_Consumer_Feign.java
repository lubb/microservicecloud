package com.lbb.springcloud.controller;

import com.lbb.springcloud.entity.Deptment;
import com.lbb.springcloud.service.DeptmentClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DeptmentController_Consumer_Feign {

    @Autowired
    private DeptmentClientService deptmentClientService;

    @RequestMapping("/consumer/deptment/add")
    public boolean add(Deptment deptment){
        return deptmentClientService.add(deptment);
    }

    @RequestMapping("/consumer/deptment/get/{id}")
    public Deptment get(@PathVariable("id") Long id){
        return deptmentClientService.get(id);
    }

    @RequestMapping("/consumer/deptment/list")
    public List<Deptment> list(Deptment deptment){
        return deptmentClientService.list();
    }
}
