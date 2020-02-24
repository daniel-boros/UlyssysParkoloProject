package MBeans;

import parkoloPackageDTO.Auto;
import parkoloPackageDTO.Parkolo;

import parkoloPackageDTO.Parkolo;
import java.util.ArrayList;
import java.util.Arrays;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import MBeans.DataBase;

/**
 * A parkoloMBean osztály, amely tartalmazza a parkolóhoz tartozó Üzleti Logikát
 * @author Daniel Boros
 */
@ManagedBean(name = "parkoloMBean")
@RequestScoped
public class ParkoloMBean {

    public String nev;
    public int ferohely;
    
    @EJB
    private DataBase database;
    
/*    public static final ArrayList<Parkolo> parkoloAutoList = 
                    new ArrayList<Parkolo>(Arrays.asList(
*/                            
                            /*new Auto("abc-123","opellll", "corsa", "feher"),  
                            new Auto("bcg-234","volvo", "850R", "piros"),    
                            new Auto("plk-789","bmwwwww", "320d", "kek"), 
                            new Auto("xyz-987","mercedes", "gt", "zold")*/
  /*                          
                            new Parkolo("Debrecen", 15, new ArrayList<Auto>(Arrays.asList(new Auto("abc-123","opellll", "corsa", "feher")))),  
                            new Parkolo("Budapest", 25, new ArrayList<Auto>(Arrays.asList(new Auto("bcg-234","volvo", "850R", "piros")))),    
                            new Parkolo("Győr", 10, new ArrayList<Auto>(Arrays.asList(new Auto("plk-789","bmwwwww", "320d", "kek")))) 
                            
                                                                       
                    ));
*/
    /**
     * getParkoloAutoList, egy előre létrehozott "adatbázisból" adja vissza a parkolók előre felvett listáját, 
     * amit a DataBase osztályban tárolunk autListB néven.
     * Ezt a @EJB annotáción keresztül érjünk el.
     * @return ParkoloAutoListát ad vissza 
     */
    
    public ParkoloMBean() {
    }

    public ArrayList<Parkolo> getParkoloAutoList() {
        return database.getParkoloAutoListB();
    }
    
    public int getFerohelyOfDebrecen() {
        int tmp = 0;
        
        for (int i = 0; i < database.getParkoloAutoListB().size(); i++) {
            if (database.getParkoloAutoListB().get(i).nev.compareTo("Debrecen") == 0) {
                tmp = database.getParkoloAutoListB().get(i).ferohely;
            }
        }
        
        return tmp;
    }

    public int getFerohelyOfBudapest() {
        int tmp = 0;
        
        for (int i = 0; i < database.getParkoloAutoListB().size(); i++) {
            if (database.getParkoloAutoListB().get(i).nev.compareTo("Budapest") == 0) {
                tmp = database.getParkoloAutoListB().get(i).ferohely;
            }
        }
        
        return tmp;
    }
    
    public int getFerohelyOfGyőr() {
        int tmp = 0;
        
        for (int i = 0; i < database.getParkoloAutoListB().size(); i++) {
            if (database.getParkoloAutoListB().get(i).nev.compareTo("Győr") == 0) {
                tmp = database.getParkoloAutoListB().get(i).ferohely;
            }
        }
        
        return tmp;
    }
    
    public int getParkoloAutoDarabOfDebrecen() {
        
        ArrayList<Auto> tmp = new ArrayList<Auto>();
        int count = 0;
        
        for (int i = 0; i < database.getAutoListB().size(); i++) {
            if (database.getAutoListB().get(i).parkolo != null) {
                if (database.getAutoListB().get(i).parkolo.compareTo("Debrecen") == 0
                        || database.getAutoListB().get(i).parkolo.compareTo("debrecen") == 0) {
                    count++;
                }
            }
        }
        
        return count;
    }
    
    public int getParkoloAutoDarabOfBudapest() {
        
        ArrayList<Auto> tmp = new ArrayList<Auto>();
        int count = 0;
        
        for (int i = 0; i < database.getAutoListB().size(); i++) {
            if (database.getAutoListB().get(i).parkolo != null) {
                if (database.getAutoListB().get(i).parkolo.compareTo("Budapest") == 0
                        || database.getAutoListB().get(i).parkolo.compareTo("budapest") == 0) {
                    count++;
                }
            }
        }
        
        return count;
    }
    
    public int getParkoloAutoDarabOfGyőr() {
        
        ArrayList<Auto> tmp = new ArrayList<Auto>();
        int count = 0;
        
        for (int i = 0; i < database.getAutoListB().size(); i++) {
            if (database.getAutoListB().get(i).parkolo != null) {
                if (database.getAutoListB().get(i).parkolo.compareTo("Győr") == 0
                        || database.getAutoListB().get(i).parkolo.compareTo("győr") == 0) {
                    count++;
                }
            }
        }
        
        return count;
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
