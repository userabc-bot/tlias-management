package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.pojo.PageResult;
import org.example.pojo.Result;
import org.example.pojo.Student;
import org.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
@Slf4j
public class StudentController {
    @Autowired
    private StudentService studentService;

    //1.学员列表查询
    @GetMapping
    public Result selectStudentList(String name,
                                    Integer degree,
                                    Integer clazzId,
                                    @RequestParam(defaultValue = "1") Integer page,
                                    @RequestParam(defaultValue = "10") Integer pageSize) {
        //调用service层
        PageResult studentPageResult=studentService.selectStudentList(name,degree,clazzId,page,pageSize);
        //返回数据
        return Result.success(studentPageResult);
    }

    //2.删除学生
    @DeleteMapping("/{ids}")
    public Result deleteStudentById(@PathVariable Integer[] ids) {
        //调用service
        studentService.deleteStudentById(ids);
        //返回数据
        return Result.success();
    }

    //3.添加学员
    @PostMapping
    public Result insertStudent(@RequestBody Student student) {
        //调用service层
        studentService.insertStudent(student);
        //返回数据
        return Result.success();
    }

    //4.根据id查询学生
    @GetMapping("/{id}")
    public Result selectStudentById(@PathVariable Integer id) {
        //调用service层
        Student student=studentService.selectStudentById(id);
        //返回数据
        return Result.success(student);
    }

    //5.修改学员
    @PutMapping
    public Result updateStudent(@RequestBody Student student){
        //调用service层
        studentService.updateStudent(student);
        //返回数据
        return Result.success();
    }

    //6.违纪处理
    @PutMapping("/violation/{id}/{score}")
    public Result updateViolationScoreById(@PathVariable Integer id, @PathVariable Integer score) {
        //调用service层
        studentService.updateViolationScoreById(id, score);
        //返回数据
        return Result.success();
    }
}
