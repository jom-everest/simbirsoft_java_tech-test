/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simbirsoft;

import java.util.ArrayList;
import java.util.HashMap;


public class Map_SL extends HashMap<String, ArrayList<String>> {
    
    public ArrayList<String> safeGet(String key) {
        ArrayList<String> tmp = get(key);
        return (tmp != null) ? tmp : new ArrayList<>();
    }
    
}
