/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auto_beans;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author User
 */
public class Parkolo {

    public String nev;
    public int ferohely;

    public ArrayList<Auto> parkoloList;

    public Parkolo(String nev, int ferohely, ArrayList<Auto> parkoloList) {
        this.nev = nev;
        this.ferohely = ferohely;
        this.parkoloList = parkoloList;
    }

    
    public ArrayList<Auto> getParkoloList() {
        return parkoloList;
    }

    public void setParkoloList(ArrayList<Auto> parkoloList) {
        this.parkoloList = parkoloList;
    }
        
    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public int getFerohely() {
        return ferohely;
    }

    public void setFerohely(int ferohely) {
        this.ferohely = ferohely;
    }

}
