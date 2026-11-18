package org.example.service;

import org.example.pojo.Dept;
import org.springframework.stereotype.Service;

import java.util.List;


public interface DeptService {
    //查询所有部门
    List<Dept> findAll();

    //根据ID删除所有部门
    void deleteById(Integer id);

    void add(Dept dept);

    //根据id查询所有部门数据
    Dept getById(Integer id);

    //更新部门
    void update(Dept dept);
}
