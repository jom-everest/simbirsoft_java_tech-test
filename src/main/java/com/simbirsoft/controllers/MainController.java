/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simbirsoft.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author slava
 */

@Controller
public class MainController {
    
/*    
    @GetMapping("/")
    public String getMainPage() {
        return "index";
    }
*/
    @GetMapping("/list")
    public String getMainPageTag(@RequestParam(value = "tag", required = false, defaultValue = "") String tag, Model model) {
        model.addAttribute("tag", tag);
        return "list";
    }
    
    @GetMapping("/")
    public String getTagCloud() {
        return "index";
    }
}
