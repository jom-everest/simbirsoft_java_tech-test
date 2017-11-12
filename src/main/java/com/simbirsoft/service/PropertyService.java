/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simbirsoft.service;

import com.simbirsoft.adapter.PersonDataAdapter;
import com.simbirsoft.entity.PersonInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.simbirsoft.data_loader.DataLoaderInterface;

/**
 *
 * @author slava
 */
@Component
public class PropertyService  {
    
//    @Autowired
//    private DataLoaderInterface dataLoader;
    
    @Autowired
    private PersonDataAdapter adapter;
    
    public PersonInfo getData() {
        try {
//            adapter.setLoader(dataLoader);
            return adapter.getData();
        }
        catch (DataLoaderInterface.DataLoaderException e) {
            // логгирование возникшей проблемы
            System.err.println(e.getMessage());
        }
        
        return null;
    }
    
}
