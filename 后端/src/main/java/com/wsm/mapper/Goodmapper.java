package com.wsm.mapper;

import com.wsm.proj.Good;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface Goodmapper {

//    @Select("select * from goods")
//    public List<good> list();


    public List<Good> list();

    @Delete("delete from goods where id=#{id}")
    public  void delete(Integer id);

    @Insert("insert into goods( name, kind, description, price, inventory) values (#{name},#{kind},#{description},#{price},#{inventory})")
    public void insert(Good g);

    @Update("update goods set name=#{name} ,kind=#{kind}, description=#{description},price=#{price},inventory=#{inventory} where id=#{id}")
    public void update(Good g);

    @Select("select * from goods where id=#{id}")
    public Good getById(Integer id);

    @Select("select * from goods where name like concat('%',#{s},'%') or kind like concat('%',#{s},'%') or description like concat('%',#{s},'%')")
    public  List<Good> listp(String s);

}
