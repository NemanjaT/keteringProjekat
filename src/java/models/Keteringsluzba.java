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
public class Keteringsluzba {
    int ketSluzbaID;
    String naziv,
            adresa,
            telefon,
            slikaUrl;

    public int getKetSluzbaID() {
        return ketSluzbaID;
    }

    public void setKetSluzbaID(int ketSluzbaID) {
        this.ketSluzbaID = ketSluzbaID;
    }

  

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getSlikaUrl() {
        return slikaUrl;
    }

    public void setSlikaUrl(String slikaUrl) {
        this.slikaUrl = slikaUrl;
    }
    
    public Keteringsluzba(int ketSluzbaID, String naziv, String adresa, String telefon, String slikaUrl){
//        super();
        this.ketSluzbaID = ketSluzbaID; 
        this.naziv = naziv; 
        this.adresa = adresa; 
        this.telefon = telefon; 
        this.slikaUrl = slikaUrl;
    }
}
