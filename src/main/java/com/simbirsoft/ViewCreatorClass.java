/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package summaryhtmlcreator;

/**
 *
 * @author slava
 */
abstract public class ViewCreatorClass {

    /**
     * <p>It's abstract method for creating an view for PersonInfo data</p>
     * 
     * @param personInfo
     * @throws ViewCreatorException
     */
    abstract public void create(PersonInfo personInfo) throws ViewCreatorException;
    
    class ViewCreatorException extends Exception {
        ViewCreatorException(String msg) {
            super(msg);
        }
        ViewCreatorException(Exception e) {
            super(e);
        }
    }
}
