package com.wsm.service;

import com.wsm.proj.Good;

import java.util.List;

public interface GoodService {


    List<Good> list();
    void delete(Integer id);

    void add(Good g);

    void update(Good g);

    List<Good> listp(String s);
}
