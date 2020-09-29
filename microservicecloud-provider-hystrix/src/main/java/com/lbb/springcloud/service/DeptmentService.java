package com.lbb.springcloud.service;

import com.lbb.springcloud.entity.Deptment;

import java.util.List;

public interface DeptmentService {

    public boolean add(Deptment deptment);

    public Deptment get(Long id);

    public List<Deptment> list();
}
