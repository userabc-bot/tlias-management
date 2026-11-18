package org.example.service;

import org.example.pojo.JobOption;
import org.example.pojo.ClazzListAndDataList;

import java.util.List;
import java.util.Map;

public interface ReportService {
    /**
     * 统计各个职位的员工人数
     * @return
     */
    JobOption getEmpJobData();

    /**
     * 统计员工性别信息
     */
    List<Map> getEmpGenderData();

    //5.3 学员学历统计
    List<Map> selectStudentCountGroupByDegree();

    //5.4 班级人数统计
    ClazzListAndDataList selectStudentCountGroupByClass();
}
