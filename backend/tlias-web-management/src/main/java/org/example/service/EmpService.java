package org.example.service;

import org.example.pojo.Emp;
import org.example.pojo.EmpQueryParam;
import org.example.pojo.LoginInfo;
import org.example.pojo.PageResult;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface EmpService {
    /**
     *
     * @param page 页码
     * @param pageSize 每页记录数
     * @return
     */
    PageResult<Emp> page(EmpQueryParam empQueryParam);

    //新增员工
    void save(Emp emp) throws Exception;

    //批量删除员工
    void deleteByIds(List<Integer> ids);

    /**
     * 根据ID查询员工的详细信息
     */
    Emp getInfo(Integer id);


    //更新员工信息
    void update(Emp emp);

    //6.1登录
    LoginInfo getLoginInfo(Emp emp);
}
