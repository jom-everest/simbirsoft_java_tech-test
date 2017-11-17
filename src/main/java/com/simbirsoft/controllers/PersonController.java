/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simbirsoft.controllers;

import com.simbirsoft.model.Person;
import com.simbirsoft.dao.PersonRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author slava
 */
@RestController
public class PersonController {
    
    @Autowired
    PersonRepository personRepo;
    
    @GetMapping("/person/")
    public List<Person> listAllPersons() {
        return personRepo.findAll();
    }
    
    @GetMapping("/person/{id}")
    public Person getPerson(@PathVariable("id") Long id) {
        return personRepo.findOne(id);
    }
    
    @DeleteMapping("/person/{id}")
    public void deletePerson(@PathVariable("id") Long id) {
        personRepo.delete(id);
    }

    @PutMapping("/person/{id}")
//      @Transactional
    public Person updatePerson(@RequestBody Person person) {
        return personRepo.save(person);
    }

    @PostMapping("/person/")
    public Person savePerson(@RequestBody Person person) {
        person.setId(null);
        return personRepo.save(person);
    }

}
