/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simbirsoft;

import com.simbirsoft.entity.PersonInfo;
import com.simbirsoft.data_loader.DataLoaderService;
import java.util.ArrayList;
import java.util.Properties;

/** Класс создает персональные данные PersonInfo, извлекая их из обобщенного 
 загрузчика данных DataLoaderService
 *
 * @author slava
 */
public class PersonDataAdapterFromProperties {
    private final DataLoaderService loader;
    
    public PersonDataAdapterFromProperties(DataLoaderService ld) {
        loader = ld;
    }
    
    // формирование данных PersonInfo
    public PersonInfo getData() throws DataLoaderService.DataLoaderException {
        // получение ассоциативного массива данных
        Properties map = loader.getData();
        PersonInfo pInfo = new PersonInfo();
        ArrayList<String> tmp;
        
        // извлечение необходимых данных из ассоциативного массива
        pInfo.fio = map.getProperty(PersonInfo.FIO, "");
        pInfo.dob = map.getProperty(PersonInfo.DOB, "");
        pInfo.email = map.getProperty(PersonInfo.EMAIL, "");
        pInfo.skype = map.getProperty(PersonInfo.SKYPE, "");
        pInfo.avatar = map.getProperty(PersonInfo.AVATAR, "");
        pInfo.phone = map.getProperty(PersonInfo.PHONE, "");
        
        pInfo.education = map.safeGet(PersonInfo.EDUCATION);
        pInfo.add_education = map.safeGet(PersonInfo.ADD_EDUCATION);
        pInfo.experience = map.safeGet(PersonInfo.EXPERIENCE);
        pInfo.hobbies = map.safeGet(PersonInfo.HOBBIES);
        pInfo.target = map.safeGet(PersonInfo.TARGET);
        pInfo.skills = map.safeGet(PersonInfo.SKILLS);
                
        return pInfo;
    }
}
