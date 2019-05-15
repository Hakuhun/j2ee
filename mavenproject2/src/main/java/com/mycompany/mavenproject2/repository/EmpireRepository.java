package com.mycompany.mavenproject2.repository;

import com.mycompany.mavenproject2.model.BuildUp;
import com.mycompany.mavenproject2.model.Building;
import com.mycompany.mavenproject2.model.Empire;
import com.mycompany.mavenproject2.model.MineralResource;
import com.mycompany.mavenproject2.model.Resources;
import com.mycompany.mavenproject2.model.Species;
import com.mycompany.mavenproject2.model.User;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@RequestScoped
public class EmpireRepository {
    @PersistenceContext(unitName = "HeroPu")
    EntityManager em;

    public List<Empire> findEmpire(User user, String name, String description, 
            MineralResource resource, int quantity, Building building ){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Empire> query = cb.createQuery(Empire.class);
        
        Root<Empire> empire = query.from(Empire.class);
        Join<Empire, Resources> resources = empire.join("resources");
        Join<Empire, BuildUp> buildings = empire.join("buildings");
        
        List<Predicate> pr = new ArrayList<>();
        if (user != null) {
            pr.add(cb.equal(empire.get("user"), user));
        }
        
        if (!name.isEmpty()) {
            pr.add(cb.like(empire.<String>get("name"), "%"+name+"%"));
        }
        
        if (!description.isEmpty()) {
            pr.add(cb.like(empire.<String>get("description"), "%"+description+"%"));
        }

        if(resource != null && quantity > 0){
            pr.add(cb.equal(resources.get("minerals"), resource));
            pr.add(cb.greaterThan(resources.<Integer>get("count"), quantity));
        }
        
        if (building != null) {
            pr.add(cb.equal(buildings.get("building"), building));
        }
        
         query.select(empire).where(cb.and(pr.toArray(new Predicate[pr.size()])));
         TypedQuery tq = em.createQuery(query)
                 .setFirstResult(0)
                 .setMaxResults(1);
         List<Empire> empireList = tq.getResultList();
         return empireList;
    }
    
    public void add(Empire empire){
        em.persist(empire);
    }

    public void modify(Empire empire){
        em.merge(empire);
    }
    
    public Empire getById(int pId) throws NullPointerException{
        return em.find(Empire.class, pId);
    }
    
    public List<Species> getAll(){
        return em.createNamedQuery("Empire.findAll").getResultList();
    }
}