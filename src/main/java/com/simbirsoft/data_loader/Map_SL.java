/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simbirsoft.data_loader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;


public class Map_SL extends HashMap<String, ArrayList<String>> {
    
    public ArrayList<String> safeGet(String key) {
        ArrayList tmp = get(key);
        return (tmp == null) ? (ArrayList)Collections.emptyList() : tmp;
    }
    
}
