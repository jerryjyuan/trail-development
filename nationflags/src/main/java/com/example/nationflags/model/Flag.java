package com.example.nationflags.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Flag {
 
    private String nation;
    private String flag;
    private String continent;
    
    @Id
    public String getNation() {
        return nation;   
    }        
    
    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getFlag() {
        return flag;   
    }        

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getContinent() {
        return continent;   
    }        

    public void setContinent(String continent) {
        this.continent = continent;
    }
}
