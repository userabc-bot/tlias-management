package org.example.mapper;

import org.apache.ibatis.annotations.*;
import org.example.pojo.Emp;
import org.example.pojo.EmpQueryParam;
import org.example.pojo.LoginInfo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Mapper
public interface EmpMapper {
    /*//查询员工信息
    @Select("select emp.*,dept.name as dept_name from emp left join dept on emp.dept_id = dept.id order by emp.update_time desc limit #{start},#{pageSize}")
    public List<Emp> list(Integer start,Integer pageSize);

    //获取符合条件的总记录数
    @Select("select count(*) from emp left join dept on emp.dept_id = dept.id")
    public Long count();*/


    //@Select("select emp.*,dept.name as dept_name from emp left join dept on emp.dept_id = dept.id order by emp.update_time desc")

    //public List<Emp> list(String name, Integer gender, LocalDate begin, LocalDate end);
    public List<Emp> list(EmpQueryParam empQueryParam);

    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time)\n" +
            "    values (#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime});\n")
    void insert(Emp emp);


    //批量删除员工信息
    void deleteByIds(List<Integer> ids);

    /**
     * 根据ID查询员工详细信息
     */
    Emp getById(Integer id);

    //更新员工基本信息
    void updateById(Emp emp);

    /**
     * 统计各个职位的员工人数
     */
    @MapKey("pos")
    List<Map<String, Object>> countEmpJobData();

    /**
     * 统计员工性别信息
     */
    @MapKey("name")
    List<Map> countEmpGenderData();

    //根据部门id查询部门员工数量
    @Select("select count(*)" +
            "from emp " +
            "where dept_id=#{dept_id}")
    Integer selectEmpCountByDeptId(Integer deptId);

    //6.1登录
    @Select("select id,username,name\n" +
            "from emp\n" +
            "where username=#{username} and password=#{password}")
    LoginInfo getLoginInfo(Emp emp);
}
