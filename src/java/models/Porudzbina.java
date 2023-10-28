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
public class Porudzbina {
    int porudzbinaID,
            ketSluzbaID,ukupnaCena;
    String poruceneStavke, korisnikID, vremeIsporuke,brTel,korisnik; 

    public int getPorudzbinaID() {
        return porudzbinaID;
    }

    public void setPorudzbinaID(int porudzbinaID) {
        this.porudzbinaID = porudzbinaID;
    }

    public int getKetSluzbaID() {
        return ketSluzbaID;
    }

    public void setKetSluzbaID(int ketSluzbaID) {
        this.ketSluzbaID = ketSluzbaID;
    }

    public String getPoruceneStavke() {
        return poruceneStavke;
    }

    public void setPoruceneStavke(String poruceneStavke) {
        this.poruceneStavke = poruceneStavke;
    }

    public String getKorisnikID() {
        return korisnikID;
    }

    public void setKorisnikID(String korisnikID) {
        this.korisnikID = korisnikID;
    }

    public String getVremeIsporuke() {
        return vremeIsporuke;
    }

    public void setVremeIsporuke(String vremeIsporuke) {
        this.vremeIsporuke = vremeIsporuke;
    }

    public String getBrTel() {
        return brTel;
    }

    public void setBrTel(String brTel) {
        this.brTel = brTel;
    }

    public String getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(String korisnik) {
        this.korisnik = korisnik;
    }

    public int getUkupnaCena() {
        return ukupnaCena;
    }

    public void setUkupnaCena(int ukupnaCena) {
        this.ukupnaCena = ukupnaCena;
    }

    
    
    public Porudzbina(int porudzbinaID, String poruceneStavke, String korisnikID, int ketSluzbaID, String vremeIsporuke,String brTel,String korisnik,int ukupnaCena){
        super();
        this.porudzbinaID = porudzbinaID; 
        this.poruceneStavke = poruceneStavke; 
        this.korisnikID = korisnikID; 
        this.ketSluzbaID = ketSluzbaID; 
        this.vremeIsporuke = vremeIsporuke; 
        this.brTel = brTel;
        this.korisnik = korisnik;
        this.ukupnaCena = ukupnaCena;
    }
}
