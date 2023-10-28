/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.Keteringsluzba;
import models.Porudzbina;

/**
 *
 * @author Nemanja
 */
public class PorudzbinaDB {
     String url = "jdbc:mysql://localhost:3306/ketering";
    String username = "root";
    String password = "";
    
    Connection connection;
    Statement statement;
    ResultSet resultSet;
    PreparedStatement prepared;
    
    public void connect() throws Exception {
        try{
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
            }catch(ClassNotFoundException e){
                throw new ClassNotFoundException(e.getMessage());
            }
            connection = DriverManager.getConnection(url,username,password);
            statement = connection.createStatement();
        }catch(SQLException e){
            throw new SQLException(e.getMessage());
        }
    }
    
   public void dodajPorudzbinu(Porudzbina porudzbina){
        try{
            this.connect();
            


 String upit = "INSERT INTO porudzbina" + "  (poruceneStavke, korisnikID, ketSluzbaID, vremeIsporuke, brojTelefona, korisnik, ukupnaCena) VALUES " +
        " (?, ?, ?, ?, ?, ?, ?);";
 prepared = connection.prepareStatement(upit);
       
            prepared.setString(1, porudzbina.getPoruceneStavke());
            prepared.setString(2, porudzbina.getKorisnikID());
            prepared.setInt(3, porudzbina.getKetSluzbaID());
            prepared.setString(4, porudzbina.getVremeIsporuke());
            prepared.setString(5, porudzbina.getBrTel());
            prepared.setString(6, porudzbina.getKorisnik());
            prepared.setInt(7, porudzbina.getUkupnaCena());
            System.out.println(prepared);
            
            prepared.executeUpdate();
        
                                    
        }catch(Exception e){
            System.out.println("GRESKA" + e);
        }
        }
    
    
    public List<Porudzbina> uzmiPoSluzbi(Keteringsluzba sluzba){
        List<Porudzbina> porudzbine = new ArrayList<Porudzbina>();
        try{
            this.connect();
            
            resultSet = statement.executeQuery("SELECT * FROM porudzbina WHERE ketSluzbaID="+sluzba.getKetSluzbaID());
            
            while(resultSet.next()){
                porudzbine.add(new Porudzbina(resultSet.getInt("porudzbinaID"), resultSet.getString("poruceneStavke"), 
                             resultSet.getString("korisnikID"), resultSet.getInt("ketSluzbaID"), resultSet.getString("vremeIsporuke"),
                             resultSet.getString("brojTelefona"), resultSet.getString("korisnik"), resultSet.getInt("ukupnaCena")));
            }
            
            if(porudzbine.size() > 0){
                return porudzbine;
            }
            return null;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
     public List<Porudzbina> uzmiPoUsername(String username){
        List<Porudzbina> porudzbine = new ArrayList<Porudzbina>();
        try{
            this.connect();
            
            resultSet = statement.executeQuery("SELECT * FROM porudzbina WHERE korisnikID=" + "'" +username + "'");
            
            while(resultSet.next()){
                porudzbine.add(new Porudzbina(resultSet.getInt("porudzbinaID"), resultSet.getString("poruceneStavke"), 
                             resultSet.getString("korisnikID"), resultSet.getInt("ketSluzbaID"), resultSet.getString("vremeIsporuke"),
                             resultSet.getString("brojTelefona"), resultSet.getString("korisnik"), resultSet.getInt("ukupnaCena")));
            }
            
            if(porudzbine.size() > 0){
                return porudzbine;
            }
            return null;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    public void obrisiPorudzbinu(String ID){
        try{
            this.connect();
            statement.executeUpdate("DELETE FROM porudzbina WHERE porudzbinaID=" + ID);
        }catch(Exception e){
            e.fillInStackTrace();
        }
    }
}
