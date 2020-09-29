package com.lbb.springcloud.controller;

import com.lbb.springcloud.entity.Deptment;
import com.lbb.springcloud.service.DeptmentService;
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
    public Deptment get(@PathVariable Long id){
        return deptmentService.get(id);
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
}
