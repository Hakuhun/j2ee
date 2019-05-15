package com.mycompany.mavenproject2.repository;

import com.mycompany.mavenproject2.model.MineralResource;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RequestScoped
public class MineralResourceRepository {
    
    @PersistenceContext(unitName = "HeroPu")
    EntityManager em;

    public void add(MineralResource mResource){
        em.persist(mResource);
    }

    public void modify(int id, MineralResource mr){
        em.persist(mr);
    }
    
//    public Species getByName(String pName) throws NullPointerException{
//        return em.createNamedQuery("MineralResource.findByName", Species.class).setParameter("name", pName).getSingleResult();
//    }

    public MineralResource getById(int Id) throws NullPointerException{
        return em.find(MineralResource.class, Id);
    }
    
    public List<MineralResource> getAll(){
        return em.createNamedQuery("MineralResource.findAll").getResultList();
    }
}