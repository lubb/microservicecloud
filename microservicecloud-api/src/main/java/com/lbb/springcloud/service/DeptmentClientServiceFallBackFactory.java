package com.lbb.springcloud.service;

import com.lbb.springcloud.entity.Deptment;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeptmentClientServiceFallBackFactory implements FallbackFactory<DeptmentClientService> {

    @Override
    public DeptmentClientService create(Throwable cause) {
        return new DeptmentClientService() {
            @Override
            public Deptment get(Long id) {
                return new Deptment().setDeptno(id).setDname("该ID"+id+"没有对应的信息,服务降级").setDb_source("数据库没有对应的数据");
            }

            @Override
            public List<Deptment> list() {
                return null;
            }

            @Override
            public boolean add(Deptment deptment) {
                return false;
            }
        };
    }
}
