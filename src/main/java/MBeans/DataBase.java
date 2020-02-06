/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MBeans;

import java.util.ArrayList;
import java.util.Arrays;
import javax.ejb.Stateless;
import parkoloPackageDTO.Auto;
import parkoloPackageDTO.Parkolo;
import MBeans.ParkoloMBean;

/**
 *
 * @author Daniel
 */
@Stateless
public class DataBase {
    
    private ArrayList<Auto> autoListB = 
                    new ArrayList<Auto>(Arrays.asList(                    
                   
                        new Auto("abc-123","opel", "corsa", "feher", "Budapest"),  
                        new Auto("bcg-234","volvo", "c70", "piros", "Debrecen"),    
                        new Auto("plk-789","bmw", "Isetta", "kek", "Győr"), 
                        new Auto("xyz-987","mercedes", "Rotschwein", "zold", "Debrecen")
                        
                            
	));
    
    private ArrayList<Parkolo> parkoloAutoListB = 
                    new ArrayList<Parkolo>(Arrays.asList(
                                 
                            new Parkolo("Debrecen", 15, new ArrayList<Auto>(Arrays.asList(new Auto("abc-123","opel", "corsa", "feher")))),  
                            new Parkolo("Budapest", 25, new ArrayList<Auto>(Arrays.asList(new Auto("bcg-234","volvo", "850R", "piros")))),    
                            new Parkolo("Győr", 10, new ArrayList<Auto>(Arrays.asList(new Auto("plk-789","bayerisch motoren werke", "fk-1", "kek")))) 
                            
                                                                       
                    ));

    public ArrayList<Auto> getAutoListB() {
        return autoListB;
    }

    public void setAutoListB(ArrayList<Auto> autoListB) {
        this.autoListB = autoListB;
    }

    public ArrayList<Parkolo> getParkoloAutoListB() {
        return parkoloAutoListB;
    }

    public void setParkoloAutoListB(ArrayList<Parkolo> parkoloAutoListB) {
        this.parkoloAutoListB = parkoloAutoListB;
    }

    
}
