/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simbirsoft.controllers;

import com.simbirsoft.model.Tag;
import com.simbirsoft.dao.TagRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author slava
 */

@RestController
public class TagController {
    
    @Autowired
    TagRepository tagRepo;
  
    @GetMapping("/tag/")
    public List<Tag> getAllTags() {
        return tagRepo.findAll();
    }
    
    @GetMapping("/tag/{tag}")
    public Tag getTag(@PathVariable("tag") String tag) {
        return tagRepo.findOne(tag);
    }
}
