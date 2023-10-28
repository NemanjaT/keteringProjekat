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
public class Korisnik {
    String username,
            password,
            imePrezime,
            adresa,
            brTel; 
    int poeni;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImePrezime() {
        return imePrezime;
    }

    public void setImePrezime(String imePrezime) {
        this.imePrezime = imePrezime;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getBrTel() {
        return brTel;
    }

    public void setBrTel(String brTel) {
        this.brTel = brTel;
    }

    public int getPoeni() {
        return poeni;
    }

    public void setPoeni(int poeni) {
        this.poeni = poeni;
    }
    
    public Korisnik(String username, String password, String imePrezime, String adresa, String brTel, int poeni){
        this.username = username; 
        this.password = password;
        this.imePrezime = imePrezime; 
        this.adresa = adresa; 
        this.brTel = brTel; 
        this.poeni = poeni; 
    }
}
