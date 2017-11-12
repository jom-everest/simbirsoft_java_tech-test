/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simbirsoft;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author slava
 */
@Component
class ResumeService {
    
    @Autowired
    private PersonProperties personInfo;
    
    public PersonProperties getResumeData() {
        return personInfo; 
    } 
}
