package com.mycompany.mavenproject2.service;


import com.mycompany.mavenproject2.model.BuildUp;
import com.mycompany.mavenproject2.model.Building;
import com.mycompany.mavenproject2.model.Empire;
import com.mycompany.mavenproject2.model.User;
import com.mycompany.mavenproject2.repository.EmpireRepository;
import java.util.Random;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class EmpireService{
    @Inject
    private EmpireRepository eRepo;
    
    
    public void add(Empire empire) {
        eRepo.add(empire);
    }
    
 
    
    public boolean isBuildingExists(Empire emp, Building building){
        for (BuildUp b : emp.getBuildings()) {
            if (b.getBuilding().equals(building)) {
                return true;
            }
        }
        return false;
    }
    
    public void addBuilding(Empire emp, Building building){
        if (isBuildingExists(emp, building)) {
            for (BuildUp b : emp.getBuildings()) {
                if (b.getBuilding().equals(building)) {
                    b.setCount(b.getCount()+1);
                }
            }
        }else{
            BuildUp bu = new BuildUp(building, 1);
            emp.getBuildings().add(bu);
        }

    }
    
    public boolean isExists(User u,Empire pEmpire){
        for (Empire emp : u.getEmpires()) {
            if(emp.getName().equals(pEmpire.getName()))
                return true;
        }
        return false;
    }
    
    //random  számot itt kaphatna egy ásvány.:)
    public int randomCount(){
        Random rnd = new Random();
        int n = rnd.nextInt(50);
        return n;
    }

    public Object getAll() {
        return eRepo.getAll();
    }

}