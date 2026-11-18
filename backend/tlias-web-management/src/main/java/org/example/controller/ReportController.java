package org.example.controller;


import lombok.extern.slf4j.Slf4j;
import org.example.pojo.JobOption;
import org.example.pojo.Result;
import org.example.pojo.ClazzListAndDataList;
import org.example.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping("/report")
@RestController
public class ReportController {
    @Autowired
    private ReportService reportService;

    /**
     * 统计各个职位的员工人数
     */
    @GetMapping("/empJobData")
    public Result getEmpJobData(){
        log.info("统计各个职位的员工人数");
        JobOption jobOption=reportService.getEmpJobData();
        return Result.success(jobOption);
    }


    /*统计员工性别信息*/
    @GetMapping("/empGenderData")
    public Result getEmpGenderData(){
        log.info("统计员工性别信息");
        List<Map> genderList=reportService.getEmpGenderData();
        return Result.success(genderList);
    }



    //5.3 学员学历统计
    @GetMapping("/studentDegreeData")
    public Result selectStudentCountGroupByDegree(){
        //记录日志
        log.info("学员学历统计");
        //调用service
        List<Map> studentCountGroupByDegree=reportService.selectStudentCountGroupByDegree();
        //返回数据
        return Result.success(studentCountGroupByDegree);
    }

    //5.4 班级人数统计
    @GetMapping("/studentCountData")
    public Result selectStudentCountGroupByClass(){
        //记录日志
        log.info("班级人数统计");
        //调用service
        ClazzListAndDataList studentCountGroupByClass=reportService.selectStudentCountGroupByClass();
        //返回数据
        return Result.success(studentCountGroupByClass);
    }

    //5.5 日志列表查询

}
