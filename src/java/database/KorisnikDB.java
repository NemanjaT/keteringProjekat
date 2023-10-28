/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import models.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Nemanja
 */
public class KorisnikDB {
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
    
    public Korisnik validacijaKorisnika(String username, String password) throws SQLException
    {
        try{
            this.connect();
        }catch(Exception e){
            System.out.println("Greska!" + e);
        }
        Korisnik korisnik = null; 
        
        resultSet = statement.executeQuery("SELECT * from korisnik where username='"+username+"' and password='"+password+"'");
        
        if(resultSet.next()){
            korisnik = new Korisnik(resultSet.getString("username"),resultSet.getString("password"),resultSet.getString("imePrezime"),resultSet.getString("adresa"),resultSet.getString("brTel"),resultSet.getInt("poeni"));
        }
        
        return korisnik;
    }
    
    public boolean proveraUsername(String username) throws SQLException
    {
        try{
            this.connect();
        }catch(Exception e){
            System.out.println("Greska!" + e);
        }
        
        resultSet = statement.executeQuery("SELECT * from korisnik where username='"+username+"'");
        
        if(resultSet.next())
        {
            return true;
        }else{
            return false;
        }
    }
    
    public boolean dodajKorisnika(Korisnik korisnik) throws SQLException {
        
        String upit = "INSERT INTO korisnik(username, password, imePrezime, poeni, adresa, brTel) " + "VALUES ('"
                                            + korisnik.getUsername() + "','" + korisnik.getPassword() + "','" + korisnik.getImePrezime() + "','"
                                            + korisnik.getPoeni() + "','" + korisnik.getAdresa() + "','" + korisnik.getBrTel() + "')";
        
        if(!statement.execute(upit)){
            return true;
        }
          return false; 
    }
    
    public int getBrojPoena(String username) throws SQLException {
        try{
            this.connect();
        }catch(Exception e){
            System.out.println("Greska!" + e);
        }
        
        resultSet = statement.executeQuery("SELECT * from korisnik where username='" + username + "'");
        
        if (resultSet.next()){
            return resultSet.getInt("poeni");
        }
        return 0;
    }
    
    
    public String getBrTel(String username) throws SQLException {
         try{
            this.connect();
        }catch(Exception e){
            System.out.println("Greska!" + e);
        }
         
         resultSet = statement.executeQuery("SELECT * from korisnik where username ='" + username + "'");
         
         if(resultSet.next()){
             return resultSet.getString("brTel");
         }
         return "";
    }
    
    public void azurirajPoene(String username, int poeni){
//         try{
//            this.connect();
//            statement.executeUpdate("UPDATE korisnik set poeni=" + poeni + "where username='" + username + "'" );
//        }catch(Exception e){
//            System.out.println("Greska!" + e);
//        }


                
             String upit = "UPDATE korisnik set poeni= ? where username= ?";
          
          
          
          try{
              this.connect();
              prepared = connection.prepareStatement(upit);
              prepared.setInt(1, poeni);
              prepared.setString(2, username);
              
              
              System.out.println(prepared);
              prepared.executeUpdate();
          }catch(Exception ex){
              ex.printStackTrace();
          }
         
         
    }
    
    public List<Korisnik> vratiSveKorisnike(){
        List<Korisnik> korisnici = new ArrayList<Korisnik>();
         try{
            this.connect();
            resultSet = statement.executeQuery("SELECT * from korisnik");
            
            while (resultSet.next()){
                korisnici.add(new Korisnik(resultSet.getString("username"),resultSet.getString("password"),
                        resultSet.getString("imePrezime"),resultSet.getString("adresa"),
                        resultSet.getString("brTel"),resultSet.getInt("poeni")));
            }
            return korisnici;
        }catch(Exception e){
            System.out.println("Greska!" + e);
            return null;
        }
    }
    
    public void obrisiKorisnika(String username){
         try{
            this.connect();
            
            statement.executeUpdate("DELETE FROM korisnik WHERE username='" + username + "'");
            
           
        }catch(Exception e){
            System.out.println("Greska!" + e);
        }
    }
    
    public void azurirajKorisnika(Korisnik korisnik) throws SQLException{
        
            
//           String upit = "UPDATE korisnik SET "
//                                    + "imePrezime'" + korisnik.getImePrezime()+ "',"
//                                    + "poeni=" + korisnik.getPoeni() + ","
//                                    + "adresa='" + korisnik.getAdresa() + "'" 
//                                    + "brTel='" + korisnik.getBrTel() + "'"
//                                    + " WHERE username='" + korisnik.getUsername()+ "'";

          String upit = "UPDATE korisnik set imePrezime= ?, adresa= ?, brTel= ?, poeni= ? where username= ?";
          
          boolean uspesno;
          
          try{
              prepared = connection.prepareStatement(upit);
              prepared.setString(1, korisnik.getImePrezime());
              prepared.setString(2, korisnik.getAdresa());
              prepared.setString(3, korisnik.getBrTel());
              prepared.setInt(4, korisnik.getPoeni());
              prepared.setString(5, korisnik.getUsername());
              
              System.out.println(prepared);
              prepared.executeUpdate();
          }catch(Exception ex){
              ex.printStackTrace();
          }
          
          
           
    }
    
    public Korisnik uzmiKorisnika(String username){
        Korisnik korisnik = null; 
        try{
            this.connect();
            
            resultSet = statement.executeQuery("SELECT * from korisnik where username='"+username+"'");
            
            if(resultSet.next()){
                korisnik = new Korisnik(resultSet.getString("username"), resultSet.getString("password"),
                resultSet.getString("imePrezime"),resultSet.getString("adresa"),resultSet.getString("brTel"), resultSet.getInt("poeni"));
            }
            
            return korisnik;
        }catch(Exception e){
            e.printStackTrace();
            return korisnik;
        }
    }
    
}
