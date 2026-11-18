package org.example.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.mapper.EmpExprMapper;
import org.example.mapper.EmpMapper;
import org.example.pojo.*;
import org.example.service.EmpLogService;
import org.example.service.EmpService;
import org.example.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;


@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private EmpExprMapper empExprMapper;

    @Autowired
    private EmpLogService empLogService;

    //更新员工信息
    @Transactional
    @Override
    public void update(Emp emp) {
        //更新员工的基本信息
        emp.setUpdateTime(LocalDate.now());
        empMapper.updateById(emp);
        //更新员工的工作经历信息，删除老的
        empExprMapper.deleteByEmpIds(Arrays.asList(emp.getId() ));
        //更新员工的工作经历信息，增加新的
        Integer empId = emp.getId();
        List<EmpExpr> exprList = emp.getExprList();
        /*if(!CollectionUtils.isEmpty(exprList)){
            exprList.forEach(empExpr -> empExpr.setEmpId(empId));
            empExprMapper.insertBatch(exprList);
        }*/
        if (!CollectionUtils.isEmpty(exprList)){
            exprList.forEach(empExpr -> empExpr.setEmpId(empId));
            empExprMapper.insertBatch(exprList);
        }
    }

    //6.1登录
    @Override
    public LoginInfo getLoginInfo(Emp emp) {
        //进一步处理

        //调用mapper层
        LoginInfo loginInfo=empMapper.getLoginInfo(emp);
        //根据mapper层返回的数据进一步处理（生成token(JWT令牌)，并赋值给loginInfo）
        Map<String,Object> claims=new HashMap<>();
        claims.put("id",loginInfo.getId());
        claims.put("username",loginInfo.getUsername());

        String jwt = JwtUtils.generateToken(claims);

        loginInfo.setToken(jwt);
        //返回数据
        return loginInfo;
    }


    /*@Override
    public PageResult<Emp> page(Integer page, Integer pageSize) {
        //1.调用mapper接口，查询总记录数
        Long total = empMapper.count();
        //2.调用mapper接口，查询结果列表
        Integer start=(page-1)*pageSize;
        List<Emp> rows = empMapper.list(start, pageSize);
        //3.封装结果PageResult
        return new PageResult<Emp>(total,rows);
    }*/

    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        //1.设置分页参数
        PageHelper.startPage(empQueryParam.getPage(),empQueryParam.getPageSize());
        //2.执行查询
        List<Emp> empList = empMapper.list(empQueryParam);
        //3.解析查询结果，并封装
        Page<Emp> p = (Page<Emp>) empList;
        return new PageResult<Emp>(p.getTotal(),p.getResult());
    }

    @Transactional(rollbackFor = {Exception.class})//事务控制
    //新增员工
    @Override
    public void save(Emp emp) throws Exception {
        try {
            //1.保存员工基本信息
            emp.setCreateTime(LocalDate.now());
            emp.setUpdateTime(LocalDate.now());
            empMapper.insert(emp);



            //2.保存员工的工作经历信息
            List<EmpExpr> exprList = emp.getExprList();
            if (!CollectionUtils.isEmpty(exprList)) {
                //遍历集合，为empId赋值
                exprList.forEach(empExpr -> {
                    empExpr.setEmpId(emp.getId());
                });
                empExprMapper.insertBatch(exprList);
            }
        } finally {
            //记录操作日志
            EmpLog empLog = new EmpLog(null,LocalDateTime.now(),"新增员工"+emp);
            empLogService.insertLog(empLog);
        }


    }


    /*@Transactional
    @Override
    public void deleteByIds(List<Integer> ids) {
        //1. 根据ID批量删除员工基本信息
        empMapper.deleteByIds(ids);

        //2. 根据员工的ID批量删除员工的工作经历信息
        empExprMapper.deleteByEmpIds(ids);
    }*/

    //批量删除员工
    @Transactional
    @Override
    public void deleteByIds(List<Integer> ids) {
       //1.根据ID批量删除员工的基本信息
        empMapper.deleteByIds(ids);

        //2.根据员工的ID批量删除员工的工作经历信息
        empExprMapper.deleteByEmpIds(ids);
    }

    @Override
    public Emp getInfo(Integer id) {
        return empMapper.getById(id);
    }


}
