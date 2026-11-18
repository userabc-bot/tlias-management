package org.example.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.exception.BusinessException;
import org.example.mapper.ClazzMapper;
import org.example.mapper.StudentMapper;
import org.example.pojo.Clazz;
import org.example.pojo.PageResult;
import org.example.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    private ClazzMapper clazzMapper;
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public PageResult page(String name, LocalDate begin, LocalDate end, Integer page, Integer pageSize) {
        //开启分页
        PageHelper.startPage(page, pageSize);
        //调用mapper
        List<Clazz> dataList=clazzMapper.list(name,begin,end);
        Page<Clazz> p=(Page<Clazz>) dataList;
        //返回数据
        return new PageResult(p.getTotal(),p.getResult());
    }

    //删除班级
    @Override
    public void deleteById(Integer id) {
        //1.查询班级下是否有学员
        Integer count=studentMapper.countByClazzId(id);
        //2.如果有学员就不能删除班级
        if (count>0){
            throw new BusinessException("班级有学员，不能删除");
        }
        //3.如果没有学员就删除班级
        clazzMapper.deleteById(id);
    }

    //添加班级
    @Override
    public void addClazz(Clazz clazz) {
        //补充信息
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        //调用mapper
        clazzMapper.addClazz(clazz);
    }

    //根据班级id查询班级详细信息
    @Override
    public Clazz selectClazzInfoById(Integer id) {
        //调用mapper
        Clazz clazz = clazzMapper.selectClazzInfoById(id);
        //返回Clazz信息
        return clazz;
    }

    //修改班级
    @Override
    public void updteClazz(Clazz clazz) {
        //调用数据
        clazzMapper.updateClazz(clazz);
    }

    //6.查询所有班级
    @Override
    public Clazz[] selectAllClazz() {
        //调用mapper层
        Clazz[] clazzes=clazzMapper.selectAllClazz();
        //返回结果
        return clazzes;
    }
}







