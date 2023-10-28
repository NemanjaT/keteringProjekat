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

import models.Menadzer;

/**
 *
 * @author Nemanja
 */
public class MenadzerDB {
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
    
    
     public Menadzer validacijaMenadzera(String username, String password) throws SQLException
    {
        try{
            this.connect();
        }catch(Exception e){
            System.out.println("Greska!" + e);
        }
        Menadzer menadzer = null; 
        
        resultSet = statement.executeQuery("SELECT * from menadzer where username='"+username+"' and password='"+password+"'");
        
        if(resultSet.next()){
            menadzer = new Menadzer(resultSet.getString("username"),resultSet.getString("password"),resultSet.getInt("ketSluzbaID"), resultSet.getString("brTelefona"),resultSet.getString("imePrezime"));
        }
        
        return menadzer;
    }
     
     
     public boolean dodajMenadzera(Menadzer menadzer) throws SQLException {
        
        String upit = "INSERT INTO menadzer(username, password, ketSluzbaID, imePrezime, brTelefona) " + "VALUES ('"
                                            + menadzer.getUsername() + "','" + menadzer.getPassword() + "','" + menadzer.getKetSluzbaID()+ "','"
                                            + menadzer.getImePrezime() + "','"  + menadzer.getBrTelefona() + "')";
        
       if(!statement.execute(upit)){
           return true;
       }
       return false;
 
    }
     
     
     public List<Menadzer> uzmiSveMenadzere(){
         List<Menadzer> menadzeri = new ArrayList<Menadzer>();
         try{
             this.connect();
             resultSet = statement.executeQuery("SELECT * from menadzer");
             
             while(resultSet.next()){
                 menadzeri.add(new Menadzer(resultSet.getString("username"), resultSet.getString("password"),resultSet.getInt("ketSluzbaID")
                               , resultSet.getString("imePrezime"), resultSet.getString("brTelefona") ));
             }
             
             return menadzeri;
         }catch(Exception e){
             e.printStackTrace();
             return null;
         }
     }
     
      public boolean proveraUsername(String username) throws SQLException
    {
        try{
            this.connect();
        }catch(Exception e){
            System.out.println("Greska!" + e);
        }
        
        resultSet = statement.executeQuery("SELECT * from menadzer where username='"+username+"'");
        
        if(resultSet.next())
        {
            return true;
        }else{
            return false;
        }
    }
      
      
      public void obrisiMenadzera(String username){
         try{
            this.connect();
            
            statement.executeUpdate("DELETE FROM menadzer WHERE username='" + username + "'");
            
           
        }catch(Exception e){
            System.out.println("Greska!" + e);
        }
    }
      
      public Menadzer uzmiMenadzera(String username){
        Menadzer menadzer = null; 
        try{
            this.connect();
            
            resultSet = statement.executeQuery("SELECT * from menadzer where username='"+username+"'");
            
            if(resultSet.next()){
                menadzer = new Menadzer(resultSet.getString("username"), resultSet.getString("password"),
                resultSet.getInt("ketSluzbaID"),resultSet.getString("imePrezime"),resultSet.getString("brTelefona"));
            }
            
            return menadzer;
        }catch(Exception e){
            e.printStackTrace();
            return menadzer;
        }
    }
      
      
      public void azurirajMenadzera(Menadzer menadzer) throws SQLException{
          
           String upit = "UPDATE menadzer set ketSluzbaID= ?, imePrezime= ?, brTelefona= ? where username= ?";
          
          
          
          try{
              prepared = connection.prepareStatement(upit);
              prepared.setInt(1, menadzer.getKetSluzbaID());
              prepared.setString(2, menadzer.getImePrezime());
              prepared.setString(3, menadzer.getBrTelefona());
              prepared.setString(4, menadzer.getUsername());
              
              System.out.println(prepared);
              prepared.executeUpdate();
          }catch(Exception ex){
              ex.printStackTrace();
          }
          
      }
    
}
