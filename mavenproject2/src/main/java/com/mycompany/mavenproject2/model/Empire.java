package com.mycompany.mavenproject2.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

// birodalomnak a neve, leírása, és épületekből áll.
@Entity
@Table(name = "empire")
@NamedQueries({
    @NamedQuery(name = "Empire.findAll", query = "SELECT e FROM Empire e")
})
public class Empire implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "name", length = 20)
    private String name;
    
    @Column(name = "description")
    @Lob
    private String description;

    @OneToMany(cascade = CascadeType.ALL)
    private List<BuildUp> buildings = new ArrayList<>();
    
    @OneToMany(cascade = CascadeType.ALL)
    private List<Resources> resources = new ArrayList<>();
    
    @ManyToOne()
    private User user;


    public Empire() {
    
    }

    public Empire(String name, String description) {
        this.name = name;
        this.description = description;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<BuildUp> getBuildings() {
        return buildings;
    }

    public void setBuildings(List<BuildUp> buildings) {
        this.buildings = buildings;
    }
    
    public List<Resources> getResources() {
        return resources;
    }

    public void setResources(List<Resources> resources) {
        this.resources = resources;
    }
    
    public void setUser(User user)    {
        this.user = user;
    }
    
    public User getUser(){
        return this.user;
    }
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Empire other = (Empire) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Empire{" + "id=" + id + ", name=" + name + ", description=" + description + ", buildings=" + buildings + ", resources=" + resources + '}';
    }
    
    
}
