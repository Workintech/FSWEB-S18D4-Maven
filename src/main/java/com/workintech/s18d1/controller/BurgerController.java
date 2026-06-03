package com.workintech.s18d1.controller;

import com.workintech.s18d1.dao.BurgerDao;
import com.workintech.s18d1.entity.BreadType;
import com.workintech.s18d1.entity.Burger;
import com.workintech.s18d1.exceptions.BurgerException;
import com.workintech.s18d1.util.BurgerValidation;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping({"/burger", "/workintech/burgers"})
public class BurgerController {

    private final BurgerDao burgerDao;

    @Autowired
    public BurgerController(BurgerDao burgerDao) {
        this.burgerDao = burgerDao;
    }

    @GetMapping
    public List<Burger> findAll() {
        log.info("Request received to find all burgers");
        return burgerDao.findAll();
    }

    @GetMapping("/{id}")
    public Burger findById(@PathVariable("id") Long id) {
        log.info("Request received to find burger by id: {}", id);
        if (id == null || id < 0) {
            throw new BurgerException("Id must be positive", HttpStatus.BAD_REQUEST);
        }
        return burgerDao.findById(id);
    }

    @PostMapping
    public Burger save(@RequestBody Burger burger) {
        log.info("Request received to save burger: {}", burger);
        BurgerValidation.validateBurger(burger);
        return burgerDao.save(burger);
    }

    @PutMapping
    public Burger update(@RequestBody Burger burger) {
        log.info("Request received to update burger: {}", burger);
        BurgerValidation.validateBurger(burger);
        return burgerDao.update(burger);
    }

    @PutMapping("/{id}")
    public Burger updateWithId(@PathVariable("id") Long id, @RequestBody Burger burger) {
        log.info("Request received to update burger with id: {} and body: {}", id, burger);
        burger.setId(id);
        BurgerValidation.validateBurger(burger);
        return burgerDao.update(burger);
    }

    @DeleteMapping("/{id}")
    public Burger delete(@PathVariable("id") Long id) {
        log.info("Request received to delete burger by id: {}", id);
        if (id == null || id < 0) {
            throw new BurgerException("Id must be positive", HttpStatus.BAD_REQUEST);
        }
        return burgerDao.remove(id);
    }

    @GetMapping("/price/{price}")
    public List<Burger> findByPrice(@PathVariable("price") Integer price) {
        log.info("Request received to find burgers with price greater than: {}", price);
        return burgerDao.findByPrice(price);
    }

    @GetMapping("/breadType/{breadType}")
    public List<Burger> findByBreadType(@PathVariable("breadType") String breadType) {
        log.info("Request received to find burgers with breadType: {}", breadType);
        try {
            BreadType type = BreadType.valueOf(breadType.toUpperCase());
            return burgerDao.findByBreadType(type);
        } catch (IllegalArgumentException e) {
            throw new BurgerException("Invalid bread type: " + breadType, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/content/{content}")
    public List<Burger> findByContent(@PathVariable("content") String content) {
        log.info("Request received to find burgers containing content: {}", content);
        return burgerDao.findByContent(content);
    }

    @GetMapping("/findByPrice")
    public List<Burger> findByPriceBody(@RequestBody PriceDto priceDto) {
        log.info("Request received to find burgers with price greater than (body): {}", priceDto);
        if (priceDto == null || priceDto.getPrice() == null) {
            throw new BurgerException("Price is required in request body", HttpStatus.BAD_REQUEST);
        }
        return burgerDao.findByPrice(priceDto.getPrice());
    }

    @GetMapping("/findByBreadType")
    public List<Burger> findByBreadTypeBody(@RequestBody BreadTypeDto breadTypeDto) {
        log.info("Request received to find burgers with breadType (body): {}", breadTypeDto);
        if (breadTypeDto == null || breadTypeDto.getBreadType() == null) {
            throw new BurgerException("BreadType is required in request body", HttpStatus.BAD_REQUEST);
        }
        return burgerDao.findByBreadType(breadTypeDto.getBreadType());
    }

    @GetMapping("/findByContent")
    public List<Burger> findByContentBody(@RequestBody ContentDto contentDto) {
        log.info("Request received to find burgers containing content (body): {}", contentDto);
        if (contentDto == null || contentDto.getContent() == null) {
            throw new BurgerException("Content is required in request body", HttpStatus.BAD_REQUEST);
        }
        return burgerDao.findByContent(contentDto.getContent());
    }

    @Data
    public static class PriceDto {
        private Integer price;
    }

    @Data
    public static class BreadTypeDto {
        private BreadType breadType;
    }

    @Data
    public static class ContentDto {
        private String content;
    }
}
