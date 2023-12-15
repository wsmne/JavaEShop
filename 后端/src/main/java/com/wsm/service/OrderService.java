package com.wsm.service;

import com.wsm.proj.Good;
import com.wsm.proj.Order;

import java.util.List;

public interface OrderService {

    List<Order> GetIdOrders(Integer id);

    void Add(Order o);

    void Delete(Order o);

    void Subbit(Order o);

    void AddDeal(Order o);

    List<Order> GetDeals();

//    void add(Order o);
//
//    void delete(Integer id);
}
