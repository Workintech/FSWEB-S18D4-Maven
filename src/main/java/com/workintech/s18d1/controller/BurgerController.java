package com.workintech.s18d1.controller;

import com.workintech.s18d1.dao.BurgerDao;
import com.workintech.s18d1.entity.BreadType;
import com.workintech.s18d1.entity.Burger;
import com.workintech.s18d1.exceptions.BurgerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BurgerController {

    @Autowired
    private BurgerDao burgerDao;

    @GetMapping("/burger")
    public List<Burger> getAllBurgers(){
        return burgerDao.findAll();
    }

    @GetMapping("/burger/{id}")
    public Burger getBurgerWithId(@PathVariable long id){
        return burgerDao.findById(id);
    }

    @PostMapping("/burger")
    public Burger saveBurger(@RequestBody Burger burger){
        return burgerDao.save(burger);
    }

    @PutMapping("/{id}")
    public Burger updateBurger(@RequestBody Burger burger){
        return burgerDao.update(burger);
    }

    @DeleteMapping("/burger/{id}")
    public Burger deleteBurger(@PathVariable long id){
        return burgerDao.remove(id);
    }

    @GetMapping("/burger/price/{price}")
    public List<Burger> getFindByPrice(@PathVariable Double price){
        return burgerDao.findByPrice(price);
    }

    @GetMapping("/burger/breadType/{breadType}")
    public List<Burger> getFindByBreadType(@PathVariable BreadType breadType){
        return burgerDao.findByBreadType(breadType);
    }

    @GetMapping("/burger/content/{content}")
    public List<Burger> getFindByContent(@PathVariable String content){
        return burgerDao.findByContent(content);
    }

}