package com.mycompany.mavenproject2.service;

import com.mycompany.mavenproject2.model.Species;
import com.mycompany.mavenproject2.repository.SpeciesRepository;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class SpeciesService {
    @Inject
    private SpeciesRepository sRepo;
    public Object getAll() {
        return sRepo.getAll();
    }

    public Species getById(int speciesID) {
        return sRepo.getById(speciesID);
    }
    
}
