package com.mycompany.mavenproject2.service;

import com.mycompany.mavenproject2.model.Building;
import com.mycompany.mavenproject2.repository.BuildingRepository;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class BuildingService {
    @Inject
    private BuildingRepository bRepo;

    public Object getAll() {
        return bRepo.getAll();
    }

    public Building getById(int parseInt) {
        return bRepo.getById(parseInt);
    }
    
    
}
