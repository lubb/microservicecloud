package com.lbb.springcloud.mapper;

import com.lbb.springcloud.entity.Deptment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DeptmentMapper {

    public Deptment findById(Long deptno);

    public boolean addDept(Deptment deptment);

    public List<Deptment> findAll();
}
