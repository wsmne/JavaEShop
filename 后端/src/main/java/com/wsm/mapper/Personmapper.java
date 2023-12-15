package com.wsm.mapper;

import com.wsm.proj.Person;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface Personmapper {
    @Select("select * from person where username= #{username} and password= #{password} ")
     Person getByUsernameAndPassword(Person person);


    @Select("select * from person")
    public List<Person> Getusers();

    @Delete("delete from person where id=#{id}")
    public  void delete(Integer id);


    @Select("select * from person where username=#{username}")
    public  List<Person> getByUsername(String username);

    @Insert("insert into person(username,password) values (#{username},#{password})")
    void AddUser(Person p);
}
