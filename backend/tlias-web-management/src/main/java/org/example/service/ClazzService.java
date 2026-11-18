package org.example.service;

import org.example.pojo.Clazz;
import org.example.pojo.PageResult;

import java.time.LocalDate;

public interface ClazzService {

    /**
     * 条件分页查询班级信息
     * @param begin
     * @param end
     * @param page
     * @param pageSize
     * @return
     */
    PageResult page(String name,LocalDate begin, LocalDate end, Integer page, Integer pageSize);

    //删除班级
    void deleteById(Integer id);

    //添加班级
    void addClazz(Clazz clazz);

    //根据班级id查询班级详细信息
    Clazz selectClazzInfoById(Integer id);

    //修改班级
    void updteClazz(Clazz clazz);

    //6.查询所有班级
    Clazz[] selectAllClazz();
}
