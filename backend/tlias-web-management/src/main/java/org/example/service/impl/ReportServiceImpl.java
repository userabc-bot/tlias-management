package org.example.service.impl;

import org.example.mapper.EmpMapper;
import org.example.mapper.StudentMapper;
import org.example.pojo.JobOption;
import org.example.pojo.ClazzListAndDataList;
import org.example.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    StudentMapper studentMapper;

    @Override
    public JobOption getEmpJobData() {
        List<Map<String,Object>> list=empMapper.countEmpJobData();
        List<Object> jobList = list.stream().map(dataMap -> dataMap.get("pos")).toList();
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("total")).toList();
        return new JobOption(jobList,dataList);
    }

    /**
     * 统计员工性别信息
     */
    @Override
    public List<Map> getEmpGenderData() {
        return empMapper.countEmpGenderData();
    }

    //5.3 学员学历统计
    @Override
    public List<Map> selectStudentCountGroupByDegree() {
        //进一步处理
        //调用mapper
        List<Map> studentCountGroupByDegree =studentMapper.selectStudentCountGroupByDegree();
        //返回数据
        return studentCountGroupByDegree;
    }

    //5.4 班级人数统计
    @Override
    public ClazzListAndDataList selectStudentCountGroupByClass() {
        //进一步处理
        //调用mapper层
        List<Map<String,Object>> studentCountGroupByClass=studentMapper.selectStudentCountGroupByClass();
        //将mapper层返回的数据进一步处理
        //将List<Map<String,Object>>通过stream流转化成ClazzListAndDataList
        //如果在List集合上使用stream流，List集合不能为空，否则会报错
        if (!CollectionUtils.isEmpty(studentCountGroupByClass)) {
            List<Object> clazzList = studentCountGroupByClass.stream().map(map -> map.get("name")).toList();
            List<Object> dataList = studentCountGroupByClass.stream().map(map -> map.get("count")).toList();
            return new ClazzListAndDataList(clazzList,dataList);
        }
        return null;
        //返回数据
    }
}


