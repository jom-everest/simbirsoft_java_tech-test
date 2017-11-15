/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simbirsoft;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author slava
 */

@Controller
public class MainController {
    @GetMapping("/")
    public String getMainPage() {
        return "index";
    }
 
}
