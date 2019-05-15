package com.mycompany.mavenproject2.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/*
    Valósítsd meg az ásványkincsek(név, leírás) tárolását és kezelést JPA alapokon.
    Készíts egy Entity-t
*/
@Entity
@Table(name = "mineralresource")
@NamedQueries({
    //Lehessen név szerint rendezve lekérdezni az összes elemet. 
    @NamedQuery(name = "MineralResource.findAll", query = "SELECT mr FROM MineralResource mr ORDER BY mr.name"),
    //Lehessen ID alapján lekérdezni,
    @NamedQuery(name = "MineralResource.findById", query = "SELECT mr FROM MineralResource mr WHERE mr.id = :id"),
    @NamedQuery(name = "MineralResource.findByName", query = "SELECT mr FROM MineralResource mr WHERE mr.name = :name")
})
public class MineralResource implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "description")
    @Lob
    private String description;
    
    public MineralResource(){
    }

    public MineralResource(int id, String name, String description){
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
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.name);
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
        final MineralResource other = (MineralResource) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

}