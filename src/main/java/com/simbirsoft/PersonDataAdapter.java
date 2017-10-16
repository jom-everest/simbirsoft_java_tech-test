/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package summaryhtmlcreator;

import java.util.ArrayList;

/** Класс создает персональные данные PersonInfo, извлекая их из обобщенного 
 * загрузчика данных DataLoaderClass
 *
 * @author slava
 */
public class PersonDataAdapter {
    final private DataLoaderClass loader;
    
    public PersonDataAdapter(DataLoaderClass ld) {
        loader = ld;
    }
    
    // формирование данных PersonInfo
    public PersonInfo getData() throws DataLoaderClass.DataLoaderException {
        // получение ассоциативного массива данных
        Map_SL map = loader.getData();
        PersonInfo pInfo = new PersonInfo();
        ArrayList<String> tmp;
        
        // извлечение необходимых данных из ассоциативного массива
        pInfo.fio = ((tmp = map.get("fio")) == null) || tmp.isEmpty() ? "" : tmp.get(0);
        pInfo.dob = ((tmp = map.get("dob")) == null) || tmp.isEmpty() ? "" : tmp.get(0);
        pInfo.email = ((tmp = map.get("email")) == null) || tmp.isEmpty() ? "" : tmp.get(0);
        pInfo.skype = ((tmp = map.get("skype")) == null) || tmp.isEmpty() ? "" : tmp.get(0);
        pInfo.avatar = ((tmp = map.get("avatar")) == null) || tmp.isEmpty() ? "" : tmp.get(0);
        pInfo.phone = ((tmp = map.get("phone")) == null) || tmp.isEmpty() ? "" : tmp.get(0);
        
        pInfo.education = (tmp = map.get("education")) != null ? tmp : new ArrayList<>();
        pInfo.add_education = (tmp = map.get("additional education")) != null ? tmp : new ArrayList<>();
        pInfo.experience = (tmp = map.get("experience")) != null ? tmp : new ArrayList<>();
        pInfo.hobbies = (tmp = map.get("hobbies")) != null ? tmp : new ArrayList<>();
        pInfo.target = (tmp = map.get("target")) != null ? tmp : new ArrayList<>();
        pInfo.skills = (tmp = map.get("skills")) != null ? tmp : new ArrayList<>();
                
        return pInfo;
    }
    
}
