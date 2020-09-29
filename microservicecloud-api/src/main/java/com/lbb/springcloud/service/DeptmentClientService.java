package com.lbb.springcloud.service;

import com.lbb.springcloud.entity.Deptment;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "MICROSERVICECLOUD-PROVIDER", fallbackFactory = DeptmentClientServiceFallBackFactory.class)
public interface DeptmentClientService {

    @RequestMapping(value = "/deptment/get/{id}", method = RequestMethod.GET)
    public Deptment get(@PathVariable("id") Long id);

    @RequestMapping(value = "/deptment/list", method = RequestMethod.GET)
    public List<Deptment> list();

    @RequestMapping(value = "/deptment/add", method = RequestMethod.POST)
    public boolean add(Deptment deptment);
}
