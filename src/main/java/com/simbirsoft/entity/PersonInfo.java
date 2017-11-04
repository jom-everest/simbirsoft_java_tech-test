/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simbirsoft.entity;

import java.util.List;

/** Открытая структура, содержащие некоторые данные по конкретному человеку 
 *
 * @author slava
 */
public class PersonInfo {
    public static final String FIO = "fio";
    public static final String DOB = "dob";
    public static final String EMAIL = "email";
    public static final String SKYPE = "skype";
    public static final String AVATAR = "avatar";
    public static final String EDUCATION = "education";
    public static final String PHONE = "phone";
    public static final String ADD_EDUCATION = "add_education";
    public static final String EXPERIENCE = "experience";
    public static final String HOBBIES = "hobbies";
    public static final String TARGET = "target";
    public static final String SKILLS = "skills";

    public String fio;  // фамилия
    public String dob;
    public String email;
    public String skype;
    public String avatar;
    public String phone;
    public List<String> education;
    public List<String> add_education;
    public List<String> experience;
    public List<String> hobbies;
    public List<String> target;
    public List<String> skills;
}


