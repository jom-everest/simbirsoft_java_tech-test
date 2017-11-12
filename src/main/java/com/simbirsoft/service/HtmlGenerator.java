/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simbirsoft.service;

import com.simbirsoft.entity.PersonInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.simbirsoft.view_creator.ViewCreatorInterface;

/**
 *
 * @author slava
 */
@Component
public class HtmlGenerator {
    
    @Autowired
    private PropertyService propertyService;
    
    @Autowired
    private ViewCreatorInterface viewCreator;
    
    public void createHtml() {
        try {
            PersonInfo pInfo = propertyService.getData();
            if (pInfo == null) return;
            viewCreator.create(pInfo);
        }  
//        catch (DataLoaderInterface.DataLoaderException e) {
//            System.err.println(e.getMessage());
//        } 
        catch (ViewCreatorInterface.ViewCreatorException e) {
            System.err.println(e.getMessage());
        }
        
    }
    
}
