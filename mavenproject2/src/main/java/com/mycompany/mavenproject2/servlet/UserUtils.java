/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject2.servlet;


import com.mycompany.mavenproject2.model.Empire;
import com.mycompany.mavenproject2.model.Hero;
import com.mycompany.mavenproject2.model.MineralResource;
import com.mycompany.mavenproject2.model.Resources;
import com.mycompany.mavenproject2.model.User;
import com.mycompany.mavenproject2.service.EmpireService;
import com.mycompany.mavenproject2.service.MineralResourceService;
import com.mycompany.mavenproject2.service.UserService;
import java.io.Serializable;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean(name= "userUtils")
@Dependent
public class UserUtils implements Serializable{
    
    @EJB
    UserService uService;
    
    @EJB
    EmpireService eService;
    
    @EJB
    MineralResourceService mrService; 
    
    private static Random random = new Random();
    
    User user;
    
    Empire newEmpire;
    
    Hero newHero;

    public Hero getNewHero() {
        return newHero;
    }

    public void setNewHero(Hero newHero) {
        this.newHero = newHero;
    }
    
    List<Empire> userEmpires;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Empire> getUserEmpires() {
        return userEmpires;
    }

    public void setUserEmpires(List<Empire> userEmpires) {
        this.userEmpires = userEmpires;
    }
    
    public UserUtils() {
        newEmpire = new Empire();
    }
    
    @PostConstruct
    public void initUser(){
        Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
        System.out.println(principal.getName());
        user = uService.getByName(principal.getName());
        userEmpires = user.getEmpires();
    }
    
    public void addNewEmpire() {
        newEmpire.setResources(generateResources());
        eService.add(newEmpire);
    }
    
    public Empire getNewEmpire() {
        return newEmpire;
    }
    
    public List<MineralResource> getMinerals() {
        return mrService.getAll();
    }
    
    private List<Resources> generateResources(){
        List<Resources> resources = new ArrayList<>();
        for (MineralResource mineral : mrService.getAll()) {
            //int count = eService.randomCount(); //itt add randomot hozzá
            int count = random.nextInt(100);
            Resources resource = new Resources(mineral, count); //ez lesz a végleges ásvány ami az empirehez hozzá lesz adva
            resources.add(resource);
        }
        return resources;
    } 
    
    public String getEmpiresResources(){
        String text = "";
        for (Empire userEmpire : userEmpires) {
            text += userEmpire.getName() + ": " + userEmpire.getDescription() + '\n';
            for (Resources resource : userEmpire.getResources()) {
                text += resource.getMinerals().getName() + " " + resource.getCount();
            }
        }
        return text;
    }
    
    public void addHero(){
        
        user.getHeroes().add(newHero);
    }
}
