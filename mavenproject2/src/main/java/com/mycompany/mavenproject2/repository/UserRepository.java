package com.mycompany.mavenproject2.repository;

import com.mycompany.mavenproject2.model.User;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RequestScoped
public class UserRepository {
    @PersistenceContext(unitName = "HeroPu")
    EntityManager em;
    
    public User add(User pUser){
        em.persist(pUser);
        return pUser;
    }
    
    public User modify(User user){
        em.merge(user);
        return user;
    }
    
    public User getByName(String pName) throws NullPointerException{
        return em.createNamedQuery("User.findByName", User.class).setParameter("name", pName).getSingleResult();
    }
    
    public User getByNameAndPassword(String name, String password){
        User u = em.createNamedQuery("User.findByNameAndPassword", User.class)
                .setParameter("name", name)
                .setParameter("password", password)
                .getSingleResult();
        return u;
    }  
    
    public User getById(int pId) throws NullPointerException{
        return em.find(User.class, pId);
    }
    
    public List<User> getAll(){
        return em.createNamedQuery("User.findAll").getResultList();
    }
    
    
}
