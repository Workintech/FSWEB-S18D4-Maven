package com.workintech.s18d1;

import com.workintech.s18d1.dao.BurgerDao;
import com.workintech.s18d1.dao.BurgerDaoImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class S18d1Application {
	@Bean
	public BurgerDao getBurgerDao(){
		return new BurgerDaoImpl();
	}

	public static void main(String[] args) {
		SpringApplication.run(S18d1Application.class, args);
	}

}
