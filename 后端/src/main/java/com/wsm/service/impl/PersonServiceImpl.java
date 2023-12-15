package com.wsm.service.impl;

import com.wsm.mapper.Ordermapper;
import com.wsm.mapper.Personmapper;
import com.wsm.proj.Person;
import com.wsm.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private Personmapper personmapper;

    @Autowired
    private Ordermapper om;
    @Override
    public Person login(Person person){
        return personmapper.getByUsernameAndPassword(person);
    }

    @Override
    public List<Person> Getusers() {
        return personmapper.Getusers();
    }

    @Override
    public void delete(Integer id) {
        om.DeleteUser(id);
        personmapper.delete(id);
    }

    @Override
    public List<Person> getByUsername(String s){
        return personmapper.getByUsername(s);
    }

    @Override
    public void Add(Person p) {
        personmapper.AddUser(p);
    }

}
