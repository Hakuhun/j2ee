/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject2.service;

import com.mycompany.mavenproject2.model.MineralResource;
import com.mycompany.mavenproject2.repository.MineralResourceRepository;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class MineralResourceService {
    @Inject
    private MineralResourceRepository mrRepo;

    public List<MineralResource> getAll() {
        return mrRepo.getAll();
    }

    public void add(MineralResource mineral) {
        mrRepo.add(mineral);
    }
    
    
}
