package org.example.mapper;

import org.apache.ibatis.annotations.*;
import org.example.pojo.Clazz;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface ClazzMapper {




    List<Clazz> list(String name, LocalDate begin, LocalDate end);

    //根据班级id删除班级
    @Delete("delete from clazz where id=#{id}")
    void deleteById(Integer id);

    //添加班级
    @Insert("insert into clazz values (null,#{name},#{room},#{beginDate},#{endDate},#{masterId},#{subject},#{createTime},#{updateTime})")
    void addClazz(Clazz clazz);

    //根据班级id查询班级详细信息
    @Select("select * from clazz where id=#{id}")
    Clazz selectClazzInfoById(Integer id);

    //修改班级
    void updateClazz(Clazz clazz);

    //6.查询所有班级
    @Select("select * from clazz")
    Clazz[] selectAllClazz();


}
