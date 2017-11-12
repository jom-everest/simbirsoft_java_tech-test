/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simbirsoft.view_creator;

import com.simbirsoft.entity.PersonInfo;

/**
 *
 * @author slava
 */
public interface ViewCreatorInterface {

    /**
     * <p>It's abstract method for creating an view for PersonInfo data</p>
     * 
     * @param personInfo
     * @throws ViewCreatorException
     */
    void create(PersonInfo personInfo)throws ViewCreatorException;
    
    class ViewCreatorException extends Exception {
        public ViewCreatorException(String msg) {
            super(msg);
        }
        public ViewCreatorException(Exception e) {
            super(e);
        }
    }
}


