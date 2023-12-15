package com.wsm.controller;


import com.wsm.proj.Result;
import com.wsm.proj.Good;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.wsm.service.GoodService;
import java.util.List;


@Slf4j
@RestController
@CrossOrigin
public class GoodController {

    //private static Logger log = LoggerFactory.getLogger(GoodController.class);

    @Autowired
    private GoodService gd;

    @GetMapping("/goods")
    public Result list(){
        log.info("查询全部商品");
        List<Good> gl= gd.list();
        return Result.success(gl);
    }

    @GetMapping("/{s}")
    public Result listp(@PathVariable String s){
        log.info("查询指定商品");
        List<Good> gl= gd.listp(s);
        return Result.success(gl);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("根据id删除商品"+id);
        gd.delete(id);
        return Result.success();
    }

    @PostMapping("/goods")
    public Result add(@RequestBody Good g){
        log.info("新增商品："+g);
        gd.add(g);
        return Result.success();
    }

    @PutMapping("/goods")
    public Result update(@RequestBody Good g){
        log.info("修改商品"+g);
        gd.update(g);
        return Result.success();
    }

}
