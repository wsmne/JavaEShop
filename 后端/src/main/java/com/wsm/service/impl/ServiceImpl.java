package com.wsm.service.impl;

import com.wsm.proj.Good;
import com.wsm.mapper.Goodmapper;
import com.wsm.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceImpl implements GoodService {

    @Autowired
    private Goodmapper goodmapper;
    @Override
    public List<Good> list(){
        return goodmapper.list();
    }

    @Override
    public List<Good> listp(String s){return goodmapper.listp(s);}

    @Override
    public void delete(Integer id){
        goodmapper.delete(id);
    }

    @Override
    public void add(Good g){
        goodmapper.insert(g);
    }

    @Override
    public void update(Good g){
        goodmapper.update(g);
    }

}
