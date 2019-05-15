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
@Table(name="resources")
public class Resources implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @ManyToOne(optional = false)
    private MineralResource minerals;
    
    private int count;

     public Resources() {
     }

     public Resources(MineralResource minerals, int count){
        this.minerals = minerals;
        this.count = count;
     }

     public MineralResource getMinerals() {
        return minerals;
     }

     public void setMinerals(MineralResource minerals) {
        this.minerals = minerals;
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
        hash = 59 * hash + Objects.hashCode(this.id);
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
        final Resources other = (Resources) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Resources{" + "id=" + id + ", minerals=" + minerals + ", count=" + count + '}';
    }

}