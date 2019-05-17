package com.mycompany.mavenproject2.service;

import com.mycompany.mavenproject2.model.Building;
import com.mycompany.mavenproject2.repository.BuildingRepository;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class BuildingService {
    @Inject
    private BuildingRepository bRepo;

    public List<Building> getAll() {
        return bRepo.getAll();
    }

    public Building getById(int parseInt) {
        return bRepo.getById(parseInt);
    }
    
    public void add(Building building){
        bRepo.add(building);
    }
    
}
