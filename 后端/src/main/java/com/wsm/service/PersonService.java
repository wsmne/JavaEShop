package com.wsm.service;

import com.wsm.proj.Person;

import java.util.List;


public interface PersonService {
      Person login(Person person);

      List<Person> Getusers();

      void delete(Integer id);

      List<Person> getByUsername(String s);


      void Add(Person p);
}
