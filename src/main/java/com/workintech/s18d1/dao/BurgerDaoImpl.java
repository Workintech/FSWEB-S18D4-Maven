package com.workintech.s18d1.dao;

import com.workintech.s18d1.entity.BreadType;
import com.workintech.s18d1.entity.Burger;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

public class BurgerDaoImpl implements BurgerDao{
    @Autowired
    private BurgerRepository burgerRepository;
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public void save(Burger burger) {
        burgerRepository.save(burger);

    }

    @Override
    public Burger findId(int id) {
        return null;
    }

    @Override
    public Burger findById(int id) {

        return burgerRepository.findById(id).orElse(null);
    }

    @Override
    public Collection<Burger> findAll() {
       // TypedQuery<Burger> query =entityManager.createQuery("SELECT b  burger b" ,Burger.class);
       // return query.getResultList(); bu bir seçenek bir diğer seçenek
        return burgerRepository.findAll();// buda diğer seçenek 
    }

    @Override
    public Collection<Burger> findPrice(float price) {
        return null;
    }

    @Override
    public Collection<Burger> findByPrice(float price) {

                 TypedQuery<Burger> query=entityManager
                .createQuery("SELECT b FROM Burger b WHERE b.price>:price ORDER BY price DESC",Burger.class);
                query.setParameter("price", price);

        return query.getResultList();
 }

    @Override
    public Collection<Burger> findBreadType(BreadType breadType) {

        TypedQuery<Burger> query=entityManager
                .createQuery("SELECT b FROM Burger b WHERE b.breadType>:breadType ORDER BY breadType ASC",Burger.class);
        query.setParameter("breadType", breadType   );

        return query.getResultList();
    }

    @Override
    public Collection<Burger> findByContent(String content) {

        TypedQuery<Burger> query=entityManager
                .createQuery("SELECT b FROM Burger b WHERE b.contents LİKE CONCAT('%':contents '%c') ",Burger.class);
        query.setParameter("contents", content);

        return query.getResultList();
    }

    @Override
    public void update(Burger burger) {
    entityManager.merge(burger);
    }

    @Override
    public void remove(int id) {
       //burgerRepository.deleteById(id); eger bunu başka  şekilde
        Burger b =findById(id);
        entityManager.remove(b);
        // Bunlardan hangisi kolay gelirse ...

    }

    @Override
    public Collection<Burger> findByBreadType(Object breadType) {
        return null;
    }
}
