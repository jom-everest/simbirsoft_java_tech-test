/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simbirsoft.controllers;

import com.simbirsoft.dao.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author slava
 */

@Controller
public class MainController {
    
    @Autowired
    TagRepository tagRepo;
    
    @GetMapping("/")
    public String getMainPage() {
        return "index";
    }

    @GetMapping("/{tag}")
    public ModelAndView getMainPageTag(@PathVariable("tag") String tag) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        mv.addObject("tag", tagRepo.getOne(tag));
        return mv;
    }
 
    @GetMapping("/cloud")
    public String getTagCloud() {
        return "tag_cloud";
    }
}
