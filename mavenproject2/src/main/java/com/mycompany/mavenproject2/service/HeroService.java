package com.mycompany.mavenproject2.service;

import com.mycompany.mavenproject2.model.Hibryd;
import com.mycompany.mavenproject2.model.Hero;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;

@RequestScoped
public class HeroService {

    public boolean isValid(Hero pHero){
        byte percent = 0;
        for(Hibryd hibryd: pHero.getHibryds())
            percent+=hibryd.getPercent();
        return percent == 100;
    }
    
}
