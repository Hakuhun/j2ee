package com.mycompany.mavenproject2.repository;

import com.mycompany.mavenproject2.model.Building;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RequestScoped
public class BuildingRepository {
    @PersistenceContext(unitName = "HeroPu")
    EntityManager em;
    
    public void add(Building building){
        em.persist(building);
    }
    
    public void modify(int id, Building building){
        em.persist(building);
    }

    public Building getById(int pId) throws NullPointerException{
        return em.find(Building.class, pId);
    }
    
    public List<Building> getAll(){
        return em.createNamedQuery("Building.findAll").getResultList();
    }
    
}