package com.workintech.s18d1.controller;

import com.workintech.s18d1.dao.BurgerDao;
import com.workintech.s18d1.entity.BreadType;
import com.workintech.s18d1.entity.Burger;
import com.workintech.s18d1.exceptions.BurgerException;
import com.workintech.s18d1.util.BurgerValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping({"/burger", "/workintech/burgers"})
public class BurgerController {

    private final BurgerDao burgerDao;

    public BurgerController(BurgerDao burgerDao) {
        this.burgerDao = burgerDao;
    }

    @GetMapping
    public List<Burger> findAll() {
        return burgerDao.findAll();
    }

    @GetMapping("/{id}")
    public Burger findById(@PathVariable Long id) {
        BurgerValidation.validateId(id);
        return burgerDao.findById(id);
    }

    @PostMapping
    public Burger save(@RequestBody Burger burger) {
        BurgerValidation.validateBurger(burger);
        return burgerDao.save(burger);
    }

    @PutMapping({"", "/{id}"})
    public Burger update(@PathVariable(required = false) Long id, @RequestBody Burger burger) {
        if (burger == null) {
            throw new BurgerException("Burger data cannot be null", HttpStatus.BAD_REQUEST);
        }
        if (id != null) {
            burger.setId(id);
        }
        BurgerValidation.validateId(burger.getId());
        return burgerDao.update(burger);
    }

    @DeleteMapping("/{id}")
    public Burger remove(@PathVariable Long id) {
        BurgerValidation.validateId(id);
        return burgerDao.remove(id);
    }

    @GetMapping("/price/{price}")
    public List<Burger> findByPrice(@PathVariable int price) {
        return burgerDao.findByPrice(price);
    }

    @GetMapping("/breadType/{breadType}")
    public List<Burger> findByBreadType(@PathVariable BreadType breadType) {
        return burgerDao.findByBreadType(breadType);
    }

    @GetMapping("/content/{content}")
    public List<Burger> findByContent(@PathVariable String content) {
        BurgerValidation.validateContent(content);
        return burgerDao.findByContent(content);
    }
}
