package com.lbb.springcloud.service.impl;

import com.lbb.springcloud.entity.Deptment;
import com.lbb.springcloud.mapper.DeptmentMapper;
import com.lbb.springcloud.service.DeptmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptmentServiceImpl implements DeptmentService {

    @Autowired
    private DeptmentMapper deptmentMapper;

    @Override
    public boolean add(Deptment deptment) {
        return deptmentMapper.addDept(deptment);
    }

    @Override
    public Deptment get(Long id) {
        return deptmentMapper.findById(id);
    }

    @Override
    public List<Deptment> list() {
        return deptmentMapper.findAll();
    }
}
