/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simbirsoft;

import java.util.ArrayList;

/** Открытая структура, содержащие некоторые данные по конкретному человеку 
 *
 * @author slava
 */
public class PersonInfo {
    static final String FIO = "fio";
    static final String DOB = "dob";
    static final String EMAIL = "email";
    static final String SKYPE = "skype";
    static final String AVATAR = "avatar";
    static final String EDUCATION = "education";
    static final String PHONE = "phone";
    static final String ADD_EDUCATION = "add_education";
    static final String EXPERIENCE = "experience";
    static final String HOBBIES = "hobbies";
    static final String TARGET = "target";
    static final String SKILLS = "skills";

    public String fio;  // фамилия
    public String dob;
    public String email;
    public String skype;
    public String avatar;
    public String phone;
    public ArrayList<String> education;
    public ArrayList<String> add_education;
    public ArrayList<String> experience;
    public ArrayList<String> hobbies;
    public ArrayList<String> target;
    public ArrayList<String> skills;
/*    
    
    public class Characteristic {
        public ArrayList<String> value = new ArrayList<>();
        public String name;
        public String shortName;
        public bool isMulti;
        public Characteristic(String name, String shortName, bool isMulti = false) {
            this.name = name;
            this.shortName = shortName;
            this.isMulti = isMulti;
        }
    }
    public Characteristic fio = new CharacterName("Фамилия Имя Отчество", "fio");
    public Characteristic dob = new CharacterName("Дата рождения", "dob");
    public CharacterName email = new CharacterName("e-mail", "email");
    public CharacterName skype = new CharacterName("Skype", "skype");
    public CharacterName avatar = new CharacterName("Иконка", "avatar");
    public CharacterName phone = new CharacterName("Телефон", "phone");
    
    public CharacterName education = new CharacterName("Образование", "education", true);
    public CharacterName add_education = new CharacterName("Дополнительное образование и курсы", "add_education", true);
    public CharacterName experience = new CharacterName("Опыт работы", "experience", true);
    public CharacterName hobbies = new CharacterName("Хобби", "hobbies", true);
    public CharacterName target = new CharacterName("Цель", "target", true);
    public CharacterName skills = new CharacterName("Нпвыки", "skills", true);
    
    enum TypeElem {imgRef, text};
    enum CatagoryElem {personal, prof};
    
    class str {
        String name;
        int nElem;
        TypeElem type;
        CatagoryElem cat;
    }
*/
}


