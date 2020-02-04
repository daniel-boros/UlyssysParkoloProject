/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auto_beans;

import java.util.ArrayList;
import java.util.Arrays;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author User
 */
@Named(value = "parkolo2")
@Dependent
public class Parkolo2 {

    public String nev;
    public int ferohely;
    
    public static final ArrayList<Parkolo> parkoloAutoList = 
                    new ArrayList<Parkolo>(Arrays.asList(
                            
                            /*new Auto("abc-123","opellll", "corsa", "feher"),  
                            new Auto("bcg-234","volvo", "850R", "piros"),    
                            new Auto("plk-789","bmwwwww", "320d", "kek"), 
                            new Auto("xyz-987","mercedes", "gt", "zold")*/
                            
                            new Parkolo("Debrecen", 15, new ArrayList<Auto>(Arrays.asList(new Auto("abc-123","opellll", "corsa", "feher")))),  
                            new Parkolo("Budapest", 25, new ArrayList<Auto>(Arrays.asList(new Auto("bcg-234","volvo", "850R", "piros")))),    
                            new Parkolo("Győr", 10, new ArrayList<Auto>(Arrays.asList(new Auto("plk-789","bmwwwww", "320d", "kek")))) 
                            
                                                                       
                    ));

    public ArrayList<Parkolo> getParkoloAutoList() {
        return parkoloAutoList;
    }

    public int getFerohely() {
        return ferohely;
    }

    public void setFerohely(int ferohely) {
        this.ferohely = ferohely;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }
    
    
    
}
