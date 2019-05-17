package com.mycompany.mavenproject2.servlet;

import com.mycompany.mavenproject2.model.Building;
import com.mycompany.mavenproject2.model.MineralResource;
import com.mycompany.mavenproject2.model.User;
import com.mycompany.mavenproject2.service.BuildingService;
import com.mycompany.mavenproject2.service.MineralResourceService;
import com.mycompany.mavenproject2.service.UserService;
import java.security.Principal;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "adminUtils")
@Dependent
public class AdminUtils {
    
    @EJB
    UserService uService;
    
    @EJB
    BuildingService bService;
    
    @EJB
    MineralResourceService mrService;
    
    Building newBuilding;
    
    MineralResource newResource;
    
    public AdminUtils() {
        newBuilding = new Building();
        newResource = new MineralResource();
        System.out.println("Admin oldal betöltve");
    }
    
    public void addNewBuilding(){
        bService.add(newBuilding);
    }
    
    public void addNewResource(){
        mrService.add(newResource);
    }
    
    public List<Building> getBuildings() {
        return bService.getAll();
    }

    public List<MineralResource> getMinerals() {
        return mrService.getAll();
    }

    public Building getNewBuilding() {
        return newBuilding;
    }

    public void setNewBuilding(Building newBuilding) {
        this.newBuilding = newBuilding;
    }

    public MineralResource getNewResource() {
        return newResource;
    }

    public void setNewResource(MineralResource newResource) {
        this.newResource = newResource;
    }
    
    
    

}
