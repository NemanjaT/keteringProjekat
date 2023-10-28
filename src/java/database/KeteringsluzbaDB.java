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

/**
 *
 * @author Nemanja
 */
public class KeteringsluzbaDB {
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
    
    public List<Keteringsluzba> uzmiSve() throws SQLException {
            List<Keteringsluzba> sluzbe = new ArrayList<Keteringsluzba>();
            
            resultSet = statement.executeQuery("SELECT * from keteringsluzba");
            while (resultSet.next()){
                sluzbe.add(new Keteringsluzba
                (resultSet.getInt("ketSluzbaID"), 
                 
                 resultSet.getString("naziv"), 
                 resultSet.getString("adresa"), 
                 resultSet.getString("telefon"), 
                 resultSet.getString("slikaUrl")));
                
                
                        
            }
            return sluzbe;
    }
    
    public void obrisiSluzbu(String ID){
        try{
            this.connect();
            statement.executeUpdate("DELETE FROM meni where ketSluzbaID="+ID);
            statement.executeUpdate("DELETE FROM menadzer where ketSluzbaID="+ID);
            statement.executeUpdate("DELETE FROM keteringsluzba where ketSluzbaID="+ID);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public void dodajSluzbu(Keteringsluzba sluzba){
        try{
            this.connect();
            


 String upit = "INSERT INTO keteringsluzba" + "  (ketSluzbaID, naziv, adresa, telefon, slikaUrl) VALUES " +
        " (?, ?, ?, ?, ?);";
 prepared = connection.prepareStatement(upit);
       
            prepared.setInt(1, sluzba.getKetSluzbaID());
            prepared.setString(2, sluzba.getNaziv());
            prepared.setString(3, sluzba.getAdresa());
            prepared.setString(4, sluzba.getTelefon());
            prepared.setString(5, sluzba.getSlikaUrl());
            System.out.println(prepared);
            
            prepared.executeUpdate();
        
                                    
        }catch(Exception e){
            System.out.println("GRESKA" + e);
        }
    }
    
    public void azurirajSluzbu(Keteringsluzba sluzba) throws SQLException{
        
       
            
            
//            statement.executeUpdate("UPDATE keteringsluzba SET"
//                             + "naziv=" + sluzba.getNaziv() + "',"
//                             + "adresa=" + sluzba.getAdresa() + "',"
//                             + "telefon=" + sluzba.getTelefon() + "',"
//                             + "slikaUrl=" + sluzba.getSlikaUrl() + "'"
//                         + "WHERE ketSluzbaID=" + sluzba.getKetSluzbaID());
            
            
             String upit = "UPDATE keteringsluzba set naziv= ?, adresa= ?, telefon= ?, slikaUrl= ?  where ketSluzbaID= ?";
          
          
          
          try{
               
              prepared = connection.prepareStatement(upit);
              prepared.setString(1, sluzba.getNaziv());
              prepared.setString(2, sluzba.getAdresa());
              prepared.setString(3, sluzba.getTelefon());
              prepared.setString(4, sluzba.getSlikaUrl());
              prepared.setInt(5, sluzba.getKetSluzbaID());
              
              System.out.println(prepared);
              prepared.executeUpdate();
          }catch(Exception ex){
              ex.printStackTrace();
          }
                    
        
        
    }
    
    public int novaSluzba(){
        int novID = 0;
        try{
            this.connect();
            
            resultSet = statement.executeQuery("SELECT MAX(ketSluzbaID) from keteringsluzba");
            
            while(resultSet.next()){
                novID = resultSet.getInt("MAX(ketSluzbaID)");
            }
            
            return novID + 1;
        }catch(Exception e){
            e.printStackTrace();
            return novID;
        }
    }
    
    public Keteringsluzba uzmiSluzbu(String ID){
        Keteringsluzba sluzba = null;
        
        try{
            this.connect();
            
            resultSet = statement.executeQuery("SELECT * FROM keteringsluzba where ketSluzbaID=" + ID);
            
            if(resultSet.next()){
                sluzba = new Keteringsluzba(
                              resultSet.getInt("ketSluzbaID"),
                               resultSet.getString("naziv"),
                               resultSet.getString("adresa"),
                               resultSet.getString("telefon"),
                               resultSet.getString("slikaUrl"));
            }
            return sluzba;
        }catch(Exception e){
            e.printStackTrace();
            return sluzba;
        }
    }
    
    
}
