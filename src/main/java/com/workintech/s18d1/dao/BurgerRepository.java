package com.workintech.s18d1.dao;

import com.workintech.s18d1.entity.Burger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BurgerRepository extends JpaRepository <Burger, Integer>{
}
