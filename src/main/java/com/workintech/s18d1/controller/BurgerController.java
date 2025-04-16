package com.workintech.s18d1.controller;

import com.workintech.s18d1.dao.BurgerDao;
import com.workintech.s18d1.entity.BreadType;
import com.workintech.s18d1.entity.Burger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;

@RestController
public class BurgerController {
    @Autowired
    private BurgerDao burgerDao;
    @GetMapping(path ="/workintech/burgers")
    public Collection<Burger> getAllBurgers(){
    return burgerDao.findAll();
    // bunun yerine deneyelim!!
     /*public ModelAndView getAllBurger(){
        ModelAndView mv =new ModelAndView();
        mv.getModel().put("Burgers","");
        return mv;*/
//yine 404 attı bulunamadı dedi. Mapping de hata var gibi!!

    }
    @GetMapping("/workintench/burgers/{burgersId}")
    public Burger getBurgerById(@PathVariable Integer burgerId){
    return burgerDao.findById(burgerId);
    }

    @PostMapping("/workintech/burgers")

        public void saveBurger(@RequestBody Burger burger){
        System.out.println(burger.toString());
        burgerDao.save(burger);
    }
    @DeleteMapping("/woekintech/burgers/{burgerId}")
    public void deleteBurger(@PathVariable Integer burgerId){
        burgerDao.remove(burgerId);
    }
    @PutMapping("/workintech/burgers")
    public void  updateBurger(@RequestBody Burger burger){

    }
    @GetMapping("/workintech/burgers/findByPrice")
    public Collection<Burger> getBurgersByPrice(@RequestBody float price){
        return burgerDao.findByPrice(price);
    }
    @GetMapping("/workintech/burgers/findByBreadType")
    public Collection<Burger> getBurgersByBreadType(@RequestBody Map<String, Object> data){
        BreadType breadType= BreadType.valueOf((String) data.get("bread_type"));
        return burgerDao.findByBreadType(breadType);
    }
    @GetMapping("/workintech/burgers/findByContent")
    public Collection<Burger> getBurgersByContent(@RequestBody String content){

        return burgerDao.findByContent(content);
    }

}
