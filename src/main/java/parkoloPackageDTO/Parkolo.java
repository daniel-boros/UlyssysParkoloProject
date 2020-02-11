package parkoloPackageDTO;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Parkolo osztály, amelyben a parkolók attribútumait, konstruktorát valamit getter és setter metódusait találjuk meg. 
 * @author Daniel Boros
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
