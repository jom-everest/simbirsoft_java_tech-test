/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simbirsoft;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author slava
 */
@Entity
@Table(name = "TAGS")
public class Tag {
    
    @Id
    @Column(name = "NAME")
    private String name;
    
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "tags")
    private List<Person> persons = null;

    public String getName() {
        return name;
    }
    
    public Tag() {}
    
    public Tag(String name) {
        this.name = name;
    }
    
    public Tag(String name, List<Person> persons) {
        this.name = name;
        this.persons = persons;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }
}
