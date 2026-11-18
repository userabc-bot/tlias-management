package org.example.controller;

import org.example.pojo.Clazz;
import org.example.pojo.PageResult;
import org.example.pojo.Result;
import org.example.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/clazzs")
public class ClazzController {
    @Autowired
    ClazzService clazzService;
    //查询班级
    @GetMapping
    public Result page(String name,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize){
        PageResult pageResult=clazzService.page(name,begin,end,page,pageSize);
        return Result.success(pageResult);
    }


    //删除班级
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        //调用mapper
        clazzService.deleteById(id);
        //返回数据
        return Result.success();
    }

    //添加班级
    @PostMapping
    public Result addClazz(@RequestBody Clazz clazz){
        //调用mapper
        clazzService.addClazz(clazz);
        //返回数据
        return Result.success();
    }

    //根据id查询班级详情
    @GetMapping("/{id}")
    public Result selectInfoById(@PathVariable Integer id){
        //调用service
        Clazz clazz=clazzService.selectClazzInfoById(id);
        //返回数据
        return Result.success(clazz);
    }

    //修改班级
    @PutMapping
    public Result updateClazz(@RequestBody Clazz clazz){
        //调用service
        clazzService.updteClazz(clazz);
        //返回数据
        return Result.success();
    }

    //6.查询所有班级
    @GetMapping("/list")
    public Result selectAllClazz(){
        //调用service
        Clazz[]clazzes=clazzService.selectAllClazz();
        //返回数据
        return Result.success(clazzes);
    }




}
