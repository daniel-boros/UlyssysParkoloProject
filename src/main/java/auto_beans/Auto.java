/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auto_beans;

import java.util.Date;

/**
 *
 * @author User
 */
public class Auto {

            public String rendszam;
            public String marka;
            public String tipus;
            public String szin;
            public String parkolo;
            public Date parkKezd;
            public Date parkVeg;
            
            
            public Auto(String rendszam, String marka, String tipus, String szin) {
		this.rendszam = rendszam;
		this.marka = marka;
		this.tipus = tipus;
		this.szin = szin;
            }

            public Auto(String rendszam, String marka, String tipus, String szin, String parkolo) {
                this.rendszam = rendszam;
                this.marka = marka;
                this.tipus = tipus;
                this.szin = szin;
                this.parkolo = parkolo;
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

            public void setParkolo(String parkolo) {
                this.parkolo = parkolo;
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
