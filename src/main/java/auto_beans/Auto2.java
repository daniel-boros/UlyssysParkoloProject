/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auto_beans;

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
import org.primefaces.PrimeFaces;
        

/**
 *
 * @author User
 */
@ManagedBean(name="Auto2bean")
@RequestScoped
public class Auto2 implements Serializable {
    
        private static final long serialVersionUID = 1L;
    
        public String rendszam;
	public String marka;
	public String tipus;
	public String szin;
        public String parkolo;
        public Date parkKezd;
        public Date parkVeg;
        public ArrayList<String> parkoloKocsikList = new ArrayList<String>();
        public ArrayList<String> rendszamList = new ArrayList<String>();
                                
        private static final ArrayList<Auto> autoList = 
                    new ArrayList<Auto>(Arrays.asList(                    
                   
                        new Auto("abc-123","opel", "corsa", "feher", "Budapest"),  
                        new Auto("bcg-234","volvoo", "850R", "piros", "Debrecen"),    
                        new Auto("plk-789","bmw", "320d", "kek", "Győr"), 
                        new Auto("xyz-987","mercedes", "gt", "zold", "Debrecen")
                        
                            
	));
     
        public ArrayList<Auto> getAutoList() {
 
		return autoList;
 
	}
        
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
                
                for (int i = 0; i < autoList.size(); i++) {
                    if (autoList.get(i).rendszam.compareTo(uj.rendszam) == 0) {
                        flag++;
                        System.out.println("a rendszám már szerepel az adatbázisban " + flag);
                    }
                }
		
                if (flag == 0){
                    autoList.add(uj);
                }
	}
        
 
	/*public void deleteAction(Auto auto) {
	    
		autoList.remove(auto);
	}*/
        
        public void deleteAction(String rendsza) {
	    
                if (rendsza == null || rendsza.isEmpty()) {
                    //RequestContext context = RequestContext.getCurrentInstance();
                    //context.execute("PF('requestRendsz').show();");
                }
            
		for (int i = 0; i < autoList.size(); i++) {
                    if (autoList.get(i).rendszam.compareTo(rendsza) == 0) {
                        System.out.println(rendsza + " törölve");
                        autoList.remove(i);
                    }
            }
	}

        
        /*public String getDebreceniAutok() {
            if (this.parkolo.compareTo("Debrecen") == 0) {
                return 
            }
        }*/
        
        public void setParkoloTo(String b_rendszam, String b_parkolo) {
            int flag = 0;
            
            System.out.println(b_rendszam + "|" + b_parkolo);
            
            if (b_rendszam.isEmpty() || b_rendszam == null || b_parkolo.isEmpty() || b_parkolo == null) {
                //RequestContext context = RequestContext.getCurrentInstance();
                //context.execute("PF('requestRendsz').show();");
                return;
            /*if (autoList.get(autoList.indexOf()).parkolo == null) {
                System.out.println(autoList.get(autoList.indexOf(bemeno)).parkolo + "<-----üres--------");*/
            } else {
                for (int i = 0; i < autoList.size(); i++) { 
                    if (autoList.get(i).parkolo != null){
                        System.out.println("túljutva");
                        if (autoList.get(i).parkolo.compareTo(b_parkolo) == 0) {
                            if (autoList.get(i).rendszam.compareTo(b_rendszam) == 0) {
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
                for (int i = 0; i < autoList.size(); i++) {
                    System.out.println("flag0");
                    if (autoList.get(i).rendszam.compareTo(b_rendszam) == 0) {
                        System.out.println("index=i");
                        index = i;
                    }
                }
                System.out.println(b_parkolo + " parkolóba behelyezve: " + b_rendszam);
                autoList.get(index).setParkolo(b_parkolo);
            } else {
                System.out.println("dialog meghívva");
                //RequestContext context = RequestContext.getCurrentInstance();
                //context.execute("PF('megtelt1').show();");
            }
        }
        
        public void setParkoloToDebrecen(Auto bemeno) {
            int flag = 0;
            
            if (autoList.get(autoList.indexOf(bemeno)).parkolo == null) {
                System.out.println(autoList.get(autoList.indexOf(bemeno)).parkolo + "<-----üres--------");
            } else {
                for (int i = 0; i < autoList.size(); i++) {                               
                        if (autoList.get(i).parkolo.compareTo("Debrecen") == 0) {
                            if (autoList.get(i).rendszam.compareTo(bemeno.rendszam) == 0) {
                                flag++;
                                System.out.println("a rendszám már szerepel a parkolóban " + flag);
                            }
                        }
                    }
            }

            if (flag == 0) {
                autoList.get(autoList.indexOf(bemeno)).setParkolo("Debrecen");
            } else {
                System.out.println("dialog meghívva");
                //RequestContext context = RequestContext.getCurrentInstance();
                //context.execute("PF('megtelt1').show();");
            }
        }
        public void setParkoloToBudapest(Auto bemeno) {
           int flag = 0;
            
            if (autoList.get(autoList.indexOf(bemeno)).parkolo == null) {
                System.out.println(autoList.get(autoList.indexOf(bemeno)).parkolo + "<-----üres--------");
            } else {
                for (int i = 0; i < autoList.size(); i++) {                               
                        if (autoList.get(i).parkolo.compareTo("Budapest") == 0) {
                            if (autoList.get(i).rendszam.compareTo(bemeno.rendszam) == 0) {
                                flag++;
                                System.out.println("a rendszám már szerepel a parkolóban " + flag);
                            }
                        }
                    }
            }

            if (flag == 0) {
                autoList.get(autoList.indexOf(bemeno)).setParkolo("Budapest");
            } else {
                System.out.println("dialog meghívva");
                //RequestContext context = RequestContext.getCurrentInstance();
                //context.execute("PF('megtelt1').show();");
            }
        }
        public void setParkoloToGyor(Auto bemeno) {
            int flag = 0;
            
            if (autoList.get(autoList.indexOf(bemeno)).parkolo == null) {
                System.out.println(autoList.get(autoList.indexOf(bemeno)).parkolo + "<-----üres--------");
            } else {
                for (int i = 0; i < autoList.size(); i++) {                               
                        if (autoList.get(i).parkolo.compareTo("Győr") == 0) {
                            if (autoList.get(i).rendszam.compareTo(bemeno.rendszam) == 0) {
                                flag++;
                                System.out.println("a rendszám már szerepel a parkolóban " + flag);
                            }
                        }
                    }
            }

            if (flag == 0) {
                autoList.get(autoList.indexOf(bemeno)).setParkolo("Győr");
            } else {
                System.out.println("dialog meghívva");
                //RequestContext context = RequestContext.getCurrentInstance();
                //context.execute("PF('megtelt1').show();");
            }
        }
        
        public ArrayList<Auto> getParkoloOfDebrecen() {
 
                ArrayList<Auto> tmp = new ArrayList<Auto>();
                                        
                for (int i = 0; i < autoList.size(); i++) {
                    if (autoList.get(i).parkolo != null){
                        if (autoList.get(i).parkolo.compareTo("Debrecen") == 0 || 
                                autoList.get(i).parkolo.compareTo("debrecen") == 0) tmp.add(autoList.get(i));
                    }
                }
                
		return tmp;
 
	}
        public ArrayList<Auto> getParkoloOfBudapest() {
 
                ArrayList<Auto> tmp = new ArrayList<Auto>();
            
                for (int i = 0; i < autoList.size(); i++) {
                    if (autoList.get(i).parkolo != null){
                        if (autoList.get(i).parkolo.compareTo("Budapest") == 0 ||
                                autoList.get(i).parkolo.compareTo("budapest") == 0) tmp.add(autoList.get(i));
                    }
                }
                
		return tmp;
 
	}
        public ArrayList<Auto> getParkoloOfGyor() {
 
                ArrayList<Auto> tmp = new ArrayList<Auto>();
            
                for (int i = 0; i < autoList.size(); i++) {
                    if (autoList.get(i).parkolo != null){
                        if (autoList.get(i).parkolo.compareTo("Győr") == 0 ||
                                autoList.get(i).parkolo.compareTo("győr") == 0) tmp.add(autoList.get(i));
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
        
        public void setParkolasTime(String rendsz1, Date kezd1, Date veg1) {
            System.out.println("kezd: " + kezd1 + " vég: " + veg1 + " | " + rendsz1);
            
            if (rendsz1 == null || kezd1 == null || veg1 == null) {
                //RequestContext context = RequestContext.getCurrentInstance();
                //context.execute("PF('requestParkTime').show();");
                return;
            }
            
            for (int i = 0; i < autoList.size(); i++) {
                if (autoList.get(i).rendszam != null){
                    if (autoList.get(i).rendszam.compareTo(rendsz1) == 0) {
                            autoList.get(i).setParkKezd(kezd1);
                            autoList.get(i).setParkVeg(veg1);
                            System.out.println("beállítás sikeres");
                    }
                }
            }
            
        }

        public ArrayList<String> getRendszamList() {
            rendszamList.clear();
            for (int i = 0; i < autoList.size(); i++) {
                rendszamList.add(autoList.get(i).rendszam);
            }
            return rendszamList;
        }

        public void setRendszamList(ArrayList<String> rendszamList) {
            this.rendszamList = rendszamList;
        }
               
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