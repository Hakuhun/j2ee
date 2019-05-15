package com.mycompany.mavenproject2.repository;

import com.mycompany.mavenproject2.model.Species;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RequestScoped
public class SpeciesRepository {
    @PersistenceContext(unitName = "HeroPu")
    EntityManager em;
    
    public void add(Species pSpecies){
        em.persist(pSpecies);
    }
    
    public void modify(int id, Species species){
        em.persist(species);
    }
    
    public Species getByName(String pName) throws NullPointerException{
        return em.createNamedQuery("Species.findByName", Species.class).setParameter("name", pName).getSingleResult();
    }

    public Species getById(int pId) throws NullPointerException{
        return em.find(Species.class, pId);
    }
    
    public List<Species> getAll(){
        return em.createNamedQuery("Species.findAll").getResultList();
    }
    
}
