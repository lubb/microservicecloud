package com.lbb.springcloud.controller;

import com.lbb.springcloud.entity.Deptment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class DeptmentController_Consumer {

    //private static final String REST_URL_PREFIX = "http://localhost:8001";
    private static final String REST_URL_PREFIX = "http://MICROSERVICECLOUD-PROVIDER";

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/consumer/deptment/add")
    public boolean add(Deptment deptment){
        return restTemplate.postForObject(REST_URL_PREFIX+"/deptment/add", deptment,  Boolean.class);
    }

    @RequestMapping("/consumer/deptment/get/{id}")
    public Deptment get(@PathVariable("id") Long id){
        return restTemplate.getForObject(REST_URL_PREFIX+"/deptment/get/"+id, Deptment.class);
    }

    @RequestMapping("/consumer/deptment/list")
    public List<Deptment> list(Deptment deptment){
        return restTemplate.getForObject(REST_URL_PREFIX+"/deptment/list", List.class);
    }

    @RequestMapping(value = "/consumer/deptment/discovery", method = RequestMethod.GET)
    public Object discovery(){
        return restTemplate.getForObject(REST_URL_PREFIX+"/deptment/discovery", Object.class);
    }
}
