/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auto_beans;

import java.util.ArrayList;
import java.util.Arrays;
import javax.ejb.Stateless;

/**
 *
 * @author Daniel
 */
@Stateless
public class dataBaseBean {
    
    ArrayList<Auto> autoListB = 
                    new ArrayList<Auto>(Arrays.asList(                    
                   
                        new Auto("abc-123","opellllll", "corsa", "feher", "Budapest"),  
                        new Auto("bcg-234","volvo", "850R", "piros", "Debrecen"),    
                        new Auto("plk-789","bmw", "320d", "kek", "Győr"), 
                        new Auto("xyz-987","mercedessss", "gt", "zold", "Debrecen")
                        
                            
                    ));
    
    ArrayList<Parkolo> parkoloListB = 
                    new ArrayList<Parkolo>(Arrays.asList(
                                                        
                            new Parkolo("Debrecen", 15, new ArrayList<Auto>(Arrays.asList(new Auto("abc-123","opell", "corsa", "feher")))),  
                            new Parkolo("Budapest", 25, new ArrayList<Auto>(Arrays.asList(new Auto("bcg-234","vvvolvo", "850R", "piros")))),    
                            new Parkolo("Győr", 10, new ArrayList<Auto>(Arrays.asList(new Auto("plk-789","bmwwwww", "320d", "kek")))) 
                            
                                                                       
                    ));

    public ArrayList<Auto> getAutoListB() {
        return autoListB;
    }

    public void setAutoListB(ArrayList<Auto> autoListB) {
        this.autoListB = autoListB;
    }

    public ArrayList<Parkolo> getParkoloListB() {
        return parkoloListB;
    }

    public void setParkoloListB(ArrayList<Parkolo> parkoloListB) {
        this.parkoloListB = parkoloListB;
    }
    
    
    
}
