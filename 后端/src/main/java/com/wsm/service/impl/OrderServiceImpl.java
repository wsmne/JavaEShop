package com.wsm.service.impl;

import com.wsm.mapper.Ordermapper;
import com.wsm.proj.Order;
import com.wsm.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private Ordermapper op;
    @Override
    public List<Order> GetIdOrders(Integer id){
        return op.GetIdOrders(id);
    }

    @Override
    public void Add(Order o) {
        op.Add(o);
    }

    @Override
    public void Delete(Order o) {
        op.Delete(o);
    }

    @Override
    public void Subbit(Order o) {
        op.Subbit(o);
    }

    @Override
    public void AddDeal(Order o) {
        op.AddDeal(o);
    }

    @Override
    public List<Order> GetDeals() {
        return op.GetDeals();
    }
}
