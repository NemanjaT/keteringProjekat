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
public class Menadzer {
    String username,
            password,
            imePrezime,
            brTelefona;
    int ketSluzbaID; 

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

    public String getBrTelefona() {
        return brTelefona;
    }

    public void setBrTelefona(String brTelefona) {
        this.brTelefona = brTelefona;
    }

    public int getKetSluzbaID() {
        return ketSluzbaID;
    }

    public void setKetSluzbaID(int ketSluzbaID) {
        this.ketSluzbaID = ketSluzbaID;
    }
    
    public Menadzer(String username, String password,int ketSluzbaID, String imePrezime, String brTelefona){
        this.username = username; 
        this.password = password; 
        this.imePrezime = imePrezime; 
        this.brTelefona = brTelefona;
        this.ketSluzbaID = ketSluzbaID; 
    }
}
