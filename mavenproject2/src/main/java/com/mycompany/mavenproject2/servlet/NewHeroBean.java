package com.mycompany.mavenproject2.servlet;

import com.mycompany.mavenproject2.model.Hero;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedProperty;

@Named(value = "newHeroBean")
@Dependent
public class NewHeroBean {
    
    @ManagedProperty(value = "@{hero}")
    Hero newHero;

    public Hero getNewHero() {
        return newHero;
    }

    public void setNewHero(Hero newHero) {
        this.newHero = newHero;
    }
    
    public NewHeroBean() {
        
    }
    
    
}
