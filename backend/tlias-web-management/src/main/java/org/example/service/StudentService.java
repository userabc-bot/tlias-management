package org.example.service;

import org.apache.ibatis.annotations.Insert;
import org.example.pojo.PageResult;
import org.example.pojo.Student;
import org.springframework.stereotype.Service;

@Service
public interface StudentService {



    //1.学员列表查询
    PageResult selectStudentList(String name, Integer degree, Integer clazzId, Integer page, Integer pageSize);

    //2.删除学生
    void deleteStudentById(Integer[] ids);

    //3.添加学生
    void insertStudent(Student student);

    //4.根据id查询学生
    Student selectStudentById(Integer id);

    //5.修改学员
    void updateStudent(Student student);

    //6.违纪处理
    void updateViolationScoreById(Integer id, Integer score);
}
