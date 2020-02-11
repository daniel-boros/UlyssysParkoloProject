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
    public ArrayList<Parkolo> getParkoloAutoList() {
        return database.getParkoloAutoListB();
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
