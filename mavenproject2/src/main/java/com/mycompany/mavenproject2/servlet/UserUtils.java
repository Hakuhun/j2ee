/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject2.servlet;


import com.mycompany.mavenproject2.model.Empire;
import com.mycompany.mavenproject2.model.MineralResource;
import com.mycompany.mavenproject2.service.EmpireService;
import com.mycompany.mavenproject2.service.MineralResourceService;
import com.mycompany.mavenproject2.service.UserService;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

@ManagedBean(name= "userUtils")
@Dependent
public class UserUtils {
    
    @EJB
    UserService uService;
    
    @EJB
    EmpireService eService;
    
    @EJB
    MineralResourceService mrService; 
    
    
    
    Empire newEmpire;
    
    public UserUtils() {
        newEmpire = new Empire();
    }
    
    public void addNewEmpire() {
       eService.add(newEmpire);
    }
    
    public Empire getNewEmpire() {
        return newEmpire;
    }
    
    public List<MineralResource> getMinerals() {
        return mrService.getAll();
    }
    
}
