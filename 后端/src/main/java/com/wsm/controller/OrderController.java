package com.wsm.controller;


import com.wsm.proj.Order;
import com.wsm.proj.Person;
import com.wsm.proj.Result;
import com.wsm.service.OrderService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin
public class OrderController {
    @Autowired
    private OrderService os;

    @GetMapping("/carts/{id}")
    public Result GetUserCert(@PathVariable Integer id){
        log.info("查询用户"+id+"的购物车");
        List<Order> ol= os.GetIdOrders(id);
        return Result.success(ol);
    }

    @PostMapping("/carts")
    public Result Add(@RequestBody Order o){
        log.info("添加购物车"+o);
        os.Add(o);
        return Result.success();
    }

    @DeleteMapping("/carts")
    public Result Delete(@RequestBody Order o){
        log.info("删除订单",o);
        os.Delete(o);
        return Result.success();
    }



    @PostMapping("/deals")
    public Result Adddeal(@RequestBody Order o){
        log.info("用户{}购买了{}商品{}件",o.getUid(),o.getPid(),o.getQuantity());
        os.AddDeal(o);
        return Result.success();
    }


    @PutMapping ("/carts")
    public Result Subbit(@RequestBody Order o){
        log.info("用户{}购买了{}商品{}件",o.getUid(),o.getPid(),o.getQuantity());
        os.Subbit(o);
        os.AddDeal(o);
        os.Delete(o);
        return Result.success();
    }

    @GetMapping("/deals")
    public Result GetDeals(){
        log.info("查询所有成交单");
        List<Order> ol=os.GetDeals();
        return Result.success(ol);
    }
}
