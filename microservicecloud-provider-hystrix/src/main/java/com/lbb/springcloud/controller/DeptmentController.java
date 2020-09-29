package com.lbb.springcloud.controller;

import com.lbb.springcloud.entity.Deptment;
import com.lbb.springcloud.service.DeptmentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeptmentController {

    @Autowired
    private DeptmentService deptmentService;
    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping(value = "/deptment/add",method = RequestMethod.POST)
    public boolean add(@RequestBody Deptment deptment){
        return deptmentService.add(deptment);
    }

    @RequestMapping(value = "/deptment/get/{id}",method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "processHystrix_Get")
    public Deptment get(@PathVariable Long id){
        Deptment deptment = deptmentService.get(id);
        if(null == deptment){
            throw  new RuntimeException("该ID："+id+"没有对应的信息");
        }
        return deptment;
    }

    @RequestMapping(value = "/deptment/list",method = RequestMethod.GET)
    public List<Deptment> list(){
        return deptmentService.list();
    }

    @RequestMapping(value = "/deptment/discovery", method = RequestMethod.GET)
    public Object discovery(){
       List<String> list = discoveryClient.getServices();
       System.out.println("*******"+ list);
       List<ServiceInstance> srvList = discoveryClient.getInstances("microservicecloud-provider");
       for (ServiceInstance serviceInstance : srvList){
           System.out.println(serviceInstance.getServiceId()+"\t"+ serviceInstance.getHost()+"\t"+serviceInstance.getPort()+"\t"+serviceInstance.getUri());
       }
       return this.discoveryClient;
    }

    public Deptment processHystrix_Get(@PathVariable("id") Long id){
        return new Deptment().setDeptno(id).setDname("该ID"+id+"返回为空,HystrixCommand").setDb_source("数据库没有对应的数据");
    }
}
