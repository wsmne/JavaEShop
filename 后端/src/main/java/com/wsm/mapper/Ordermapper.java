package com.wsm.mapper;


import com.wsm.proj.Order;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Mapper
public interface Ordermapper {

    @Select("SELECT uid,pid,SUM(quantity) AS quantity,price \n" +
            "FROM carts,goods \n" +
            "WHERE uid = #{dd} and pid=goods.id\n" +
            "GROUP BY pid;")
    public List<Order> GetIdOrders(Integer dd);

    @Insert("insert into carts(uid,pid,quantity) values (#{uid},#{pid},#{quantity});")
    public void Add(Order o);

    @Delete("delete from carts where uid=#{uid} and pid=#{pid}")
    void Delete(Order o);

    @Delete("delete from carts where uid=#{uid}")
    void DeleteUser(Integer uid);

    @Update("UPDATE goods\n" +
            "JOIN carts ON carts.pid = goods.id\n" +
            "SET goods.inventory = goods.inventory - #{quantity}\n" +
            "WHERE carts.pid = #{pid} AND carts.uid = #{uid};")
    void Subbit(Order o);

    @Insert("insert into deals(uid,pid,quantity,price) values (#{uid},#{pid},#{quantity},#{price});")
    public void AddDeal(Order o);

    @Select("select * from deals")
    public List<Order> GetDeals();

}
