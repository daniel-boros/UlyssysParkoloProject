package MBeans;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.context.PrimeRequestContext;
import java.lang.Object;
import javax.ejb.EJB;
import org.primefaces.PrimeFaces;

import parkoloPackageDTO.Auto;
import parkoloPackageDTO.Auto;
import MBeans.DataBase;
import javax.annotation.PostConstruct;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.pie.PieChartDataSet;
import org.primefaces.model.charts.pie.PieChartModel;

/**
 * Az Auto2 osztály, amely tartalmazza az autókhoz tartozó Üzleti Logikát
 * @author Daniel Boros
 */
@ManagedBean(name = "Auto2bean")
@RequestScoped
public class Auto2 extends ParkoloMBean implements Serializable {

    @EJB
    private DataBase database;

    public String rendszam;
    public String marka;
    public String tipus;
    public String szin;
    public String parkolo;
    public Date parkKezd;
    public Date parkVeg;
    public int ferohely;
    public ArrayList<String> parkoloKocsikList = new ArrayList<String>();
    public ArrayList<String> rendszamList = new ArrayList<String>();
    private PieChartModel pieModel;

    /*private static final ArrayList<Auto> autoList = 
                    new ArrayList<Auto>(Arrays.asList(                    
                   
                        new Auto("abc-123","opel", "corsa", "feher", "Budapest"),  
                        new Auto("bcg-234","volvoo", "850R", "piros", "Debrecen"),    
                        new Auto("plk-789","bmw", "320d", "kek", "Győr"), 
                        new Auto("xyz-987","mercedes", "gt", "zold", "Debrecen")
                        
                            
	));*/
    
    /**
     * getAutoList, egy előre létrehozott "adatbázisból" adja vissza az autók előre felvett listáját, 
     * amit a DataBase osztályban tárolunk autListB néven.
     * Ezt a @EJB annotáción keresztül érjünk el.
     * @return Auto Listát ad vissza
     */
    public ArrayList<Auto> getAutoList() {

        return database.getAutoListB();

    }

    /**
     * a createPieModel mindenképpen lefut és el lesz végezve
     */
    @PostConstruct
    public void init() {
        createPieModel();
    }
     
    /**
     * eljárás amiben 4 autó márka számára létrehozzuk a PieChartModel-t, ebben tartjuk nyílván, 
     * hogy melyik márkából számszerűsítve mennyi található.
     * a cikkelyekhez különböző színek(bgColors) és címkék(labels) rendelhetők
     */
    public void createPieModel() {
        pieModel = new PieChartModel();
        ChartData data = new ChartData();
        int vCount = 0;
        int mCount = 0;
        int bCount = 0;
        int oCount = 0;
        int flag = 0;
        
        PieChartDataSet dataSet = new PieChartDataSet();
        ArrayList<Number> values = new ArrayList<>();
        
        for (int i = 0; i < database.getAutoListB().size(); i++) {
            if (database.getAutoListB().get(i).marka.compareTo("Volvo") == 0) vCount++;
            if (database.getAutoListB().get(i).marka.compareTo("Mercedes") == 0) mCount++;
            if (database.getAutoListB().get(i).marka.compareTo("Bmw") == 0) bCount++;
            if (database.getAutoListB().get(i).marka.compareTo("Opel") == 0) oCount++;
        }
        
        values.add(vCount);
        values.add(mCount);
        values.add(bCount);
        values.add(oCount);
        
        dataSet.setData(values);
         
        List<String> bgColors = new ArrayList<>();
        bgColors.add("rgb(255, 99, 132)");
        bgColors.add("rgb(54, 162, 235)");
        bgColors.add("rgb(255, 205, 86)");
        bgColors.add("rgb(235, 64, 52)");
        dataSet.setBackgroundColor(bgColors);
         
        data.addChartDataSet(dataSet);
        List<String> labels = new ArrayList<>();
        labels.add("Volvo");
        labels.add("Mercedes");
        labels.add("Bmw");
        labels.add("Opel");
        data.setLabels(labels);
         
        pieModel.setData(data);
    }
     
    public PieChartModel getPieModel() {
        return pieModel;
    }
 
    public void setPieModel(PieChartModel pieModel) {
        this.pieModel = pieModel;
    }
    
    /**
     * Hozzáad egy autót a meglevő listánkhoz.
     * Ellenőrzi, hogy a kitöltendő mezők(Dialog) mindegyike ki van-e töltve, amennyiben nincs, hibaüzenetet dob.
     * Ellenőrzi, hogy a rendszám szerepel-e már a listában, ha igen hiba üzenetet dob.
     */
    public void addAction() {

        int flag = 0;
        Auto uj = new Auto(this.rendszam, this.marka,
                this.tipus, this.szin);

        if (uj.rendszam.isEmpty() || uj.marka.isEmpty() || uj.tipus.isEmpty() || uj.szin.isEmpty()) {
            PrimeRequestContext context = PrimeRequestContext.getCurrentInstance();
            //context.execute("PF('uresAutoAdd').show();");
            PrimeFaces.current().executeScript("PF('uresAutoAdd').show();");
            return;
        }

        for (int i = 0; i < database.getAutoListB().size(); i++) {
            if (database.getAutoListB().get(i).rendszam.compareTo(uj.rendszam) == 0) {
                flag++;
                System.out.println("a rendszám már szerepel az adatbázisban " + flag);
            }
        }

        if (flag == 0) {
            database.getAutoListB().add(uj);
        }
    }

    /**
     * Töröl egy autót a meglevő listánkból
     * @param rendsza rendszám paraméter
     */
    public void deleteAction(String rendsza) {

        if (rendsza == null || rendsza.isEmpty()) {
            //RequestContext context = RequestContext.getCurrentInstance();
            //context.execute("PF('requestRendsz').show();");
        }

        for (int i = 0; i < database.getAutoListB().size(); i++) {
            if (database.getAutoListB().get(i).rendszam.compareTo(rendsza) == 0) {
                System.out.println(rendsza + " törölve");
                database.getAutoListB().remove(i);
            }
        }
    }

    /**
     * Egy autó hozzáadása tetszőleges parkolóhoz.
     * Parkoló beállítása egy autó számára tetszőleges városra/helyre.
     * Ellenőrzi hogy van-e még szabad parkolóhely az adott parkolóban.
     * Ellenőrzi, hogy a rendszám és parkoló paraméter meglett-e adva.
     * Amennyiben igen, ellenőrzi hogy az autó szerepel-e már abban a parkolóban.
     * Ha nem, hozzáadja, ha pedig igen, hiba üzenetet dob.
     * @param b_rendszam rendszám paraméter
     * @param b_parkolo parkoló paraméter
     */
    public void setParkoloTo(String b_rendszam, String b_parkolo) {
        int flag = 0;
        
        System.out.println(b_rendszam + "|" + b_parkolo);

        if (b_parkolo.compareTo("Debrecen") == 0) {
            if (getParkoloAutoDarabOfDebrecen() == getFerohelyOfDebrecen()) {
                PrimeRequestContext context = PrimeRequestContext.getCurrentInstance();
                PrimeFaces.current().executeScript("PF('megteltParkoloDB').show();");
                return;
            }
        }
        
        if (b_parkolo.compareTo("Budapest") == 0) {
            if (getParkoloAutoDarabOfBudapest() == getFerohelyOfBudapest()) {
                PrimeRequestContext context = PrimeRequestContext.getCurrentInstance();
                PrimeFaces.current().executeScript("PF('megteltParkoloDB').show();");
                return;
            }
        }
        
        if (b_parkolo.compareTo("Győr") == 0) {
            if (getParkoloAutoDarabOfGyőr() == getFerohelyOfGyőr()) {
                PrimeRequestContext context = PrimeRequestContext.getCurrentInstance();
                PrimeFaces.current().executeScript("PF('megteltParkoloDB').show();");
                return;
            }
        }
        
        if (b_rendszam.isEmpty() || b_rendszam == null || b_parkolo.isEmpty() || b_parkolo == null) {
            //RequestContext context = RequestContext.getCurrentInstance();
            //context.execute("PF('requestRendsz').show();");
            return;
            /*if (autoList.get(autoList.indexOf()).parkolo == null) {
                System.out.println(autoList.get(autoList.indexOf(bemeno)).parkolo + "<-----üres--------");*/
        } else {
            for (int i = 0; i < database.getAutoListB().size(); i++) {
                if (database.getAutoListB().get(i).parkolo != null) {
                    System.out.println("túljutva");
                    if (database.getAutoListB().get(i).parkolo.compareTo(b_parkolo) == 0) {
                        if (database.getAutoListB().get(i).rendszam.compareTo(b_rendszam) == 0) {
                            flag++;
                            System.out.println("a rendszám már szerepel a parkolóban " + flag);
                        }
                    }
                }
            }
        }

        if (flag == 0) {
            int index = 0;
            //autoList.get(autoList.indexOf(bemeno)).setParkolo("Debrecen");
            for (int i = 0; i < database.getAutoListB().size(); i++) {
                System.out.println("flag0");
                if (database.getAutoListB().get(i).rendszam.compareTo(b_rendszam) == 0) {
                    System.out.println("index=i");
                    index = i;
                }
            }
            System.out.println(b_parkolo + " parkolóba behelyezve: " + b_rendszam);
            database.getAutoListB().get(index).setParkolo(b_parkolo);
        } else {
            System.out.println("dialog meghívva");
            //RequestContext context = RequestContext.getCurrentInstance();
            //context.execute("PF('megtelt1').show();");
        }
    }

    /*
    public void setParkoloToDebrecen(Auto bemeno) {
        int flag = 0;

        if (database.getAutoListB().get(database.getAutoListB().indexOf(bemeno)).parkolo == null) {
            System.out.println(database.getAutoListB().get(database.getAutoListB().indexOf(bemeno)).parkolo + "<-----üres--------");
        } else {
            for (int i = 0; i < database.getAutoListB().size(); i++) {
                if (database.getAutoListB().get(i).parkolo.compareTo("Debrecen") == 0) {
                    if (database.getAutoListB().get(i).rendszam.compareTo(bemeno.rendszam) == 0) {
                        flag++;
                        System.out.println("a rendszám már szerepel a parkolóban " + flag);
                    }
                }
            }
        }

        if (flag == 0) {
            database.getAutoListB().get(database.getAutoListB().indexOf(bemeno)).setParkolo("Debrecen");
        } else {
            System.out.println("dialog meghívva");
            //RequestContext context = RequestContext.getCurrentInstance();
            //context.execute("PF('megtelt1').show();");
        }
    }

    public void setParkoloToBudapest(Auto bemeno) {
        int flag = 0;

        if (database.getAutoListB().get(database.getAutoListB().indexOf(bemeno)).parkolo == null) {
            System.out.println(database.getAutoListB().get(database.getAutoListB().indexOf(bemeno)).parkolo + "<-----üres--------");
        } else {
            for (int i = 0; i < database.getAutoListB().size(); i++) {
                if (database.getAutoListB().get(i).parkolo.compareTo("Budapest") == 0) {
                    if (database.getAutoListB().get(i).rendszam.compareTo(bemeno.rendszam) == 0) {
                        flag++;
                        System.out.println("a rendszám már szerepel a parkolóban " + flag);
                    }
                }
            }
        }

        if (flag == 0) {
            database.getAutoListB().get(database.getAutoListB().indexOf(bemeno)).setParkolo("Budapest");
        } else {
            System.out.println("dialog meghívva");
            //RequestContext context = RequestContext.getCurrentInstance();
            //context.execute("PF('megtelt1').show();");
        }
    }

    public void setParkoloToGyor(Auto bemeno) {
        int flag = 0;

        if (database.getAutoListB().get(database.getAutoListB().indexOf(bemeno)).parkolo == null) {
            System.out.println(database.getAutoListB().get(database.getAutoListB().indexOf(bemeno)).parkolo + "<-----üres--------");
        } else {
            for (int i = 0; i < database.getAutoListB().size(); i++) {
                if (database.getAutoListB().get(i).parkolo.compareTo("Győr") == 0) {
                    if (database.getAutoListB().get(i).rendszam.compareTo(bemeno.rendszam) == 0) {
                        flag++;
                        System.out.println("a rendszám már szerepel a parkolóban " + flag);
                    }
                }
            }
        }

        if (flag == 0) {
            database.getAutoListB().get(database.getAutoListB().indexOf(bemeno)).setParkolo("Győr");
        } else {
            System.out.println("dialog meghívva");
            //RequestContext context = RequestContext.getCurrentInstance();
            //context.execute("PF('megtelt1').show();");
        }
    }*/

    /**
     * Vissza adja a Debrecenhez parkolóhoz tartozó autókat 
     * @return Debrecenben található autók listáját adja vissza
     */
    public ArrayList<Auto> getParkoloOfDebrecen() {

        ArrayList<Auto> tmp = new ArrayList<Auto>();

        for (int i = 0; i < database.getAutoListB().size(); i++) {
            if (database.getAutoListB().get(i).parkolo != null) {
                if (database.getAutoListB().get(i).parkolo.compareTo("Debrecen") == 0
                        || database.getAutoListB().get(i).parkolo.compareTo("debrecen") == 0) {
                    tmp.add(database.getAutoListB().get(i));
                }
            }
        }

        return tmp;

    }

    /**
     * Vissza adja a Budapesthez parkolóhoz tartozó autókat 
     * @return Budapesten található autók listáját adja vissza
     */
    public ArrayList<Auto> getParkoloOfBudapest() {

        ArrayList<Auto> tmp = new ArrayList<Auto>();

        for (int i = 0; i < database.getAutoListB().size(); i++) {
            if (database.getAutoListB().get(i).parkolo != null) {
                if (database.getAutoListB().get(i).parkolo.compareTo("Budapest") == 0
                        || database.getAutoListB().get(i).parkolo.compareTo("budapest") == 0) {
                    tmp.add(database.getAutoListB().get(i));
                }
            }
        }

        return tmp;

    }

    /**
     * Vissza adja a Győrhöz parkolóhoz tartozó autókat 
     * @return Győrben található autók listáját adja vissza
     */
    public ArrayList<Auto> getParkoloOfGyor() {

        ArrayList<Auto> tmp = new ArrayList<Auto>();

        for (int i = 0; i < database.getAutoListB().size(); i++) {
            if (database.getAutoListB().get(i).parkolo != null) {
                if (database.getAutoListB().get(i).parkolo.compareTo("Győr") == 0
                        || database.getAutoListB().get(i).parkolo.compareTo("győr") == 0) {
                    tmp.add(database.getAutoListB().get(i));
                }
            }
        }

        return tmp;

    }

    /*public void setParkolas_kezdete(String parkolni_kezd1, String rendsz1) {
            System.out.println("kezd: " + parkolni_kezd1 + "|" + rendsz1);
            //setParkol_kezd(parkolni_kezd);
        }
        
        public void setParkolas_vege(String parkolni_vegez1, String rendsz1) {
            System.out.println("kezd: " + parkolni_vegez1 + "|" + rendsz1);
            //setParkol_veg(parkolni_vegez);
        }*/
    
    /**
     * Beállítja egy Autó számára a pakrolási idő kezdtetét és végét.
     * Ellenőrzi, hogy a megadott paraméterek üresek-e.
     * @param rendsz1 rendszám paraméter
     * @param kezd1 parkolási idő kezdete paraméter
     * @param veg1 parkolási idő vége paraméter
     */
    public void setParkolasTime(String rendsz1, Date kezd1, Date veg1) {
        System.out.println("kezd: " + kezd1 + " vég: " + veg1 + " | " + rendsz1);

        if (rendsz1 == null || kezd1 == null || veg1 == null) {
            //RequestContext context = RequestContext.getCurrentInstance();
            //context.execute("PF('requestParkTime').show();");
            return;
        }

        for (int i = 0; i < database.getAutoListB().size(); i++) {
            if (database.getAutoListB().get(i).rendszam != null) {
                if (database.getAutoListB().get(i).rendszam.compareTo(rendsz1) == 0) {
                    database.getAutoListB().get(i).setParkKezd(kezd1);
                    database.getAutoListB().get(i).setParkVeg(veg1);
                    System.out.println("beállítás sikeres");
                }
            }
        }

    }

    /**
     * Visszadaja az autók rendszámaiknak egy listáját.
     * autoFelulet.xhtml fájlban kerül felhasználásra egy legördülő listához.
     * Először törli a listát, majd újra felveszi az értékeket, hogy mindig a friss/aktuális autók rendszámait mutassa.
     * @return rendszámok listáját adja vissza
     */
    public ArrayList<String> getRendszamList() {
        rendszamList.clear();
        for (int i = 0; i < database.getAutoListB().size(); i++) {
            rendszamList.add(database.getAutoListB().get(i).rendszam);
        }
        return rendszamList;
    }

    public void setRendszamList(ArrayList<String> rendszamList) {
        this.rendszamList = rendszamList;
    }
    
    /**
     * Visszadaja a parkolók egy listáját.
     * @return Parkolók listáját adja vissza
     */
    public ArrayList<String> getParkoloList() {
        parkoloKocsikList.clear();
        parkoloKocsikList.add("Debrecen");
        parkoloKocsikList.add("Budapest");
        parkoloKocsikList.add("Győr");
        return parkoloKocsikList;
    }
    
    public void setParkoloList(ArrayList<String> parkoloList) {
        this.parkoloKocsikList = parkoloList;
    }

    public Date getParkKezd() {
        return parkKezd;
    }

    public void setParkKezd(Date parkKezd) {
        this.parkKezd = parkKezd;
    }

    public Date getParkVeg() {
        return parkVeg;
    }

    public void setParkVeg(Date parkVeg) {
        this.parkVeg = parkVeg;
    }

    public String getParkolo() {
        return parkolo;
    }

    /**
     * Teszt metódus
     */
    public void tesztEcske() {
        //RequestContext context = RequestContext.getCurrentInstance();
        //context.execute("PF('megtelt1').show();");
        /*if (bemeno == null) System.out.println("null");*/
        System.out.println("-->> ");
    }

    public void setParkolo(String parkolo) {
        this.parkolo = parkolo;
        System.out.println(parkolo + "<-->" + this.parkolo);
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getRendszam() {
        return rendszam;
    }

    public void setRendszam(String rendszam) {
        this.rendszam = rendszam;
    }

    public String getSzin() {
        return szin;
    }

    public void setSzin(String szin) {
        this.szin = szin;
    }

    public String getTipus() {
        return tipus;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }

}
