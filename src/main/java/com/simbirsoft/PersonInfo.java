/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package summaryhtmlcreator;

import java.util.ArrayList;

/** Открытая структура, содержащие некоторые данные по конкретному человеку 
 *
 * @author slava
 */
public class PersonInfo {
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
    
    public class CharacterName {
        public ArrayList<String> value = new ArrayList<>();
        public String description;
        public CharacterName(String s) {
            description = s;
        }
    }
    public CharacterName fio = new CharacterName("Фамилия Имя Отчество");
    public CharacterName dob = new CharacterName("Дата рождения");
    public CharacterName email = new CharacterName("e-mail");
    public CharacterName skype = new CharacterName("Skype");
    public CharacterName avatar = new CharacterName("Иконка");
    public CharacterName phone = new CharacterName("Телефон");
    
    public CharacterName education = new CharacterName("Образование");
    public CharacterName add_education = new CharacterName("Дополнительное образование и курсы");
    public CharacterName experience = new CharacterName("Опыт работы");
    public CharacterName hobbies = new CharacterName("Хобби");
    public CharacterName target = new CharacterName("Цель");
    public CharacterName skills = new CharacterName("Нпвыки");
    
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


