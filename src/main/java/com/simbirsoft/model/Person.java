/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simbirsoft.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author slava
 */
@Entity
@Table(name = "PERSONS")
public class Person {
    @Id
    @GeneratedValue
    private Long id;
    
    private String fio;  // фамилия
    private String developer;
    private String email;
    private String hobbies;
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "person_tag",
        joinColumns = @JoinColumn(name="person_id", referencedColumnName="id"),
        inverseJoinColumns = @JoinColumn(name = "tag", referencedColumnName = "name"))
    private List<Tag> tags = null;

    public List<Tag> getTags() {
        return tags;
    }
    
   public Person() {}

    public Person(Long id, String fio, String developer, String email, String hobbies) {
        this.id = id;
        this.fio = fio;
        this.developer = developer;
        this.email = email;
        this.hobbies = hobbies;
    }
    
    public Person(Long id, String fio, String developer, String email, String hobbies, List<Tag> tags) {
        this.id = id;
        this.fio = fio;
        this.developer = developer;
        this.email = email;
        this.hobbies = hobbies;
        this.tags = tags;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }
    
    public String getFio() {
        return fio;
    }

    public String getEmail() {
        return email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeveloper() {
        return developer;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }


    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
