/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Nemanja
 */
public class Meni {
    int meniID; 
    String predjelo, glavnoJelo, dezert,nazivMenija;

    public String getNazivMenija() {
        return nazivMenija;
    }

    public void setNazivMenija(String nazivMenija) {
        this.nazivMenija = nazivMenija;
    }
    int cenaPoOsobi,ketSluzbaID;

    public int getKetSluzbaID() {
        return ketSluzbaID;
    }

    public void setKetSluzbaID(int ketSluzbaID) {
        this.ketSluzbaID = ketSluzbaID;
    }

    public int getMeniID() {
        return meniID;
    }

    public void setMeniID(int meniID) {
        this.meniID = meniID;
    }

    public String getPredjelo() {
        return predjelo;
    }

    public void setPredjelo(String predjelo) {
        this.predjelo = predjelo;
    }

    public String getGlavnoJelo() {
        return glavnoJelo;
    }

    public void setGlavnoJelo(String glavnoJelo) {
        this.glavnoJelo = glavnoJelo;
    }

    public String getDezert() {
        return dezert;
    }

    public void setDezert(String dezert) {
        this.dezert = dezert;
    }

    public int getCenaPoOsobi() {
        return cenaPoOsobi;
    }

    public void setCenaPoOsobi(int cenaPoOsobi) {
        this.cenaPoOsobi = cenaPoOsobi;
    }

    
    
    public Meni(int meniID,String nazivMenija,String predjelo, String glavnoJelo,String dezert, int cenaPoOsobi, int ketSluzbaID ){
        super();
        this.meniID = meniID; 
        this.nazivMenija = nazivMenija;
        this.predjelo = predjelo;
        this.glavnoJelo = glavnoJelo;
        this.dezert = dezert;
        this.cenaPoOsobi = cenaPoOsobi;
        this.ketSluzbaID = ketSluzbaID;
        
        
    }
}
