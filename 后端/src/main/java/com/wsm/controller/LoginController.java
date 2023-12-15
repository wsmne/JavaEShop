package com.wsm.controller;

import com.wsm.proj.Person;
import com.wsm.proj.Result;
import com.wsm.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin
public class LoginController {
    @Autowired
    private PersonService ps;

    @PostMapping("/login")
    public Result login(@RequestBody Person person){
        log.info("员工登录"+person);
        Person p=ps.login(person);
        return p!=null?Result.success(p):Result.error("用户名或密码错误");
    }

    @PostMapping("/users")
    public Result Addusers(@RequestBody Person p){
        log.info("添加用户",p);
        ps.Add(p);
        return Result.success();
    }
    @GetMapping("/users")
    public Result Getusers(){
        log.info("查询全部用户");
        List<Person> gl= ps.Getusers();
        return Result.success(gl);
    }

    @DeleteMapping("/users/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("根据id删除用户"+id);
        ps.delete(id);
        return Result.success();
    }

    @GetMapping("/users/{s}")
    public Result getByUsername(@PathVariable String s){
        log.info("查询指定用户");
        List<Person> p= ps.getByUsername(s);
        return p==null?Result.error("查无此人"):Result.success(p);
    }


}
