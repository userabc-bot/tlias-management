package org.example.mapper;

import org.apache.ibatis.annotations.*;
import org.example.pojo.PageResult;
import org.example.pojo.Student;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {

    //统计同一个班的人有几个
    @Select("select count(*) from student where clazz_id =#{id}")
    Integer countByClazzId(Integer id);

    //1.学员列表查询
    List<Student> selectStudentList(String name, Integer degree, Integer clazzId);

    //2.删除学生
    @Delete("delete from student where id=#{id}")
    void deleteStudentById(Integer id);

    //3.添加学生
    @Insert("insert into student (name, no, gender, phone, id_card, is_college, address, degree, graduation_date, clazz_id,create_time,update_time) \n" +
            "    values (#{name},#{no},#{gender},#{phone},#{idCard},#{isCollege},#{address},#{degree},#{graduationDate},#{clazzId},#{createTime},#{updateTime}) ")
    void insertStudent(Student student);

    //4.根据id查询学生
    @Select("select * from student where id=#{id}")
    Student selectStudentById(Integer id);

    //5.修改学员
    void updateStudent(Student student);

    //6.违纪处理
    @Select("update student set violation_score=#{score} where id=#{id}")
    void updateViolationScoreById(Integer id, Integer score);

    //5.3 学员学历统计
    @MapKey("degreeName")
    List<Map> selectStudentCountGroupByDegree();

    //5.4 班级人数统计
    @Select("select c.name name,count(c.id) count\n" +
            "from student s left join tlias.clazz c on c.id = s.clazz_id\n" +
            "group by c.name\n" +
            "order by count")
    List<Map<String, Object>> selectStudentCountGroupByClass();
}
