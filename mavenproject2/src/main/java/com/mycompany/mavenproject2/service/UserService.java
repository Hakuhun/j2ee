package com.mycompany.mavenproject2.service;

import com.mycompany.mavenproject2.model.Empire;
import com.mycompany.mavenproject2.repository.UserRepository;
import com.mycompany.mavenproject2.model.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class UserService {
    @Inject
    private UserRepository uRepo;
    
    public boolean login(User user){
        try {
            return uRepo.getByNameAndPassword(user.getName(), user.getPassword()) != null;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean login(String name, String password){
        try {
            return uRepo.getByNameAndPassword(name, password) != null;
        } catch (Exception e) {
            return false;
        }
    }
    
    
    public boolean isExists(String name){
        User local = uRepo.getByName(name);
        if (local != null) {
            return true;
        }else{
            return false;
        }
    }
    
    public boolean isExists(User pUser){
        for(User user:uRepo.getAll())
            if(user.equals(pUser))
                return true;
        return false;
    }
    
    public Empire getEmpireOfUserByID(User u, int id) throws Exception{
        for (Empire empire : u.getEmpires()) {
            if (empire.getId() == id) {
                return empire;
            }
        }
        throw new Exception();
    }
       
    public User add(User user){
        return this.uRepo.add(user); 
    }
    public User modify(User user){
        return this.uRepo.modify    (user); 
    }
    
    public User gerByName(String name){
        return uRepo.getByName(name);
    }
    
    public List<User> getAll(){
        return uRepo.getAll();
    }

    public User getById(Integer id) {
        return uRepo.getById(id);
    }
}
