package org.example.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.mapper.StudentMapper;
import org.example.pojo.Clazz;
import org.example.pojo.PageResult;
import org.example.pojo.Student;
import org.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    //1.学员列表查询
    @Override
    public PageResult selectStudentList(String name, Integer degree, Integer clazzId, Integer page, Integer pageSize) {
        //1.进一步处理，开启分页
        PageHelper.startPage(page, pageSize);
        //2.调用mapper
        List<Student> studentList =studentMapper.selectStudentList(name, degree,clazzId);
        Page<Student> studentPage = (Page<Student>) studentList;
        //3.返回数据
        return new PageResult(studentPage.getTotal(), studentPage.getResult());

    }

    //2.删除学生
    @Override
    public void deleteStudentById(Integer[] ids) {
        //进一步处理
        for (Integer id : ids) {
            studentMapper.deleteStudentById(id);
        }
        //调用mapper层

        //返回数据
    }

    //3.添加学生
    @Override
    public void insertStudent(Student student) {
        //给创建时间和生成时间赋值
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        //调用mapper层
        studentMapper.insertStudent(student);
    }

    //4.根据id查询学生
    @Override
    public Student selectStudentById(Integer id) {
        //进一步处理

        //调用mapper层
        Student student=studentMapper.selectStudentById(id);
        //返回数据
        return student;
    }

    //5.修改学员
    @Override
    public void updateStudent(Student student) {
        //进一步操作（修改更新时间）
        student.setUpdateTime(LocalDateTime.now());
        //调用mapper层
        studentMapper.updateStudent(student);
        //返回数据

    }

    //6.违纪处理
    @Override
    public void updateViolationScoreById(Integer id, Integer score) {
        //进一步处理
        //调用mapper层
        studentMapper.updateViolationScoreById(id,score);
        //返回数据
    }


}
