/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simbirsoft.service;

/**
 *
 * @author slava
 */
public class ViewCreatorException extends Exception {
    
    public ViewCreatorException(String msg) {
        super(msg);
    }

    public ViewCreatorException(Exception e) {
        super(e);
    }
    
}
