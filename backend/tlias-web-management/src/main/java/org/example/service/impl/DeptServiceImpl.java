package org.example.service.impl;


import org.example.exception.BusinessException;
import org.example.mapper.DeptMapper;
import org.example.mapper.EmpMapper;
import org.example.pojo.Dept;
import org.example.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private EmpMapper empMapper;

    @Override
    public List<Dept> findAll() {
        return deptMapper.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        //如果部门下有员工，提示此部门下有员工，删除不了
            //进一步处理
            //调用mapper层(根据部门id查询部门员工数量)
            Integer empCountByDeptId=empMapper.selectEmpCountByDeptId(id);
            //将mapper层返回的数据进一步处理
            if (empCountByDeptId !=0){
                throw new BusinessException("提示此部门下有员工，删除不了");
            }
            //返回数据








        deptMapper.deleteById(id);
    }

    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());

        deptMapper.insert(dept);
    }

    @Override
    public Dept getById(Integer id) {
        return deptMapper.getById(id);
    }

    @Override
    public void update(Dept dept) {
        //1.补全基础属性-updateTime
        dept.setUpdateTime(LocalDateTime.now());
        //2.调用Mapper接口方法更新部门
        deptMapper.update(dept);
    }
}










