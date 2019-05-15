package com.mycompany.mavenproject2.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/*
    Valósítsd meg épület típusok(név, leírás) tárolására és kezelését JPA alapokon. (5 pont)
    Készíts egy Entity-t
*/
@Entity
@Table(name = "building")
@NamedQueries({
    //Lehessen név szerint rendezve lekérdezni az összes elemet. 
    @NamedQuery(name = "Building.findAll", query = "SELECT b FROM Building b ORDER BY b.name"),
    //Lehessen ID alapján lekérdezni,
    @NamedQuery(name = "Building.findById", query = "SELECT b FROM Building b WHERE b.id = :id")
})
public class Building implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    
    @Basic(optional = false)
    @NotNull
    @Size(min=1,max=20)
    @Column(name="name")
    private String name;
    
    @Basic(optional = false)
    @NotNull
    @Size(min=1,max=20)
    @Column(name="description")
    private String description;

    
    public Building(){
    }

    public Building(int id, String name, String description){
        this.id=id;
        this.name=name;
        this.description=description;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.id;
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
        final Building other = (Building) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Building{" + "id=" + id + ", name=" + name + ", description=" + description + '}';
    }

}