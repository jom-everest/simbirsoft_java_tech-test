/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simbirsoft;

import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 *
 * @author slava
 */
@Configuration
@PropertySource(value="classpath:person.properties", encoding="UTF-8")
@ConfigurationProperties
public class PersonProperties {
    private String fio;  // фамилия
    private String dob;
    private String email;
    private String skype;
    private String avatar;
    private String phone;
    private List<String> education;
    private List<String> add_education;
    private List<String> experience;
    private List<String> hobbies;
    private List<String> target;
    private List<String> skills;

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEducation(List<String> education) {
        this.education = education;
    }

    public void setAdd_education(List<String> add_education) {
        this.add_education = add_education;
    }

    public void setExperience(List<String> experience) {
        this.experience = experience;
    }

    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }

    public void setTarget(List<String> target) {
        this.target = target;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }
    public void setFio(String fio) {
        this.fio = fio;
    }
    
    public String getFio() {
        return fio;
    }

    public String getDob() {
        return dob;
    }

    public String getEmail() {
        return email;
    }

    public String getSkype() {
        return skype;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getPhone() {
        return phone;
    }

    public List<String> getEducation() {
        return education;
    }

    public List<String> getAdd_education() {
        return add_education;
    }

    public List<String> getExperience() {
        return experience;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public List<String> getTarget() {
        return target;
    }

    public List<String> getSkills() {
        return skills;
    }
}
