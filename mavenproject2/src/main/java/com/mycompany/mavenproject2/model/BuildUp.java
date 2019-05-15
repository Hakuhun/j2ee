package com.mycompany.mavenproject2.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "buildup")
public class BuildUp implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @ManyToOne(optional = false)
    private Building building;
    
    private int count;

     public BuildUp() {
     }

     public BuildUp(Building buildings, int count){
        this.building = buildings;
        this.count = count;
     }

     public Building getBuilding() {
        return building;
     }

     public void setBuilding(Building buildings) {
        this.building = buildings;
     }

    public int getCount() {
        return count;
     }

     public void setCount(int count) {
        this.count = count;
     }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.id);
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
        final BuildUp other = (BuildUp) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BuildUp{" + "id=" + id + ", building=" + building + ", count=" + count + '}';
    }

}