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
import models.*;

/**
 *
 * @author Nemanja
 */
public class MeniDB {
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
    
//    public Meni nadjiPoSluzbi(Keteringsluzba sluzba){
//      Meni meni = null;
//      
//      try{
//          resultSet = statement.executeQuery("SELECT * from meni where ketSluzbaID=" + sluzba.getKetSluzbaID());
//          
//          while(resultSet.next()){
//              meni = new Meni(resultSet.getInt("meniID"), resultSet.getString("predjelo"),
//                                      resultSet.getString("glavnoJelo"), resultSet.getString("dezert"),resultSet.getInt("cenaPoOsobi"));
//          }
//          
//          return meni;
//      }catch(SQLException e){
//          e.printStackTrace();
//          return meni;
//      }
//    }
    
    
    public void dodajMeni(Meni meni) throws SQLException{
      
        try{
        this.connect();
        String upit = "INSERT INTO meni" + "  (meniID, nazivMenija, predjelo, glavnoJelo, dezert, cenaPoOsobi, ketSluzbaID) VALUES " +
        " (?, ?, ?, ?, ?, ?, ?);";
 prepared = connection.prepareStatement(upit);
       
            prepared.setInt(1, meni.getMeniID());
            prepared.setString(2, meni.getNazivMenija());
            prepared.setString(3, meni.getPredjelo());
            prepared.setString(4, meni.getGlavnoJelo());
            prepared.setString(5, meni.getDezert());
             prepared.setInt(6, meni.getCenaPoOsobi());
             prepared.setInt(7, meni.getKetSluzbaID());
            System.out.println(prepared);
            
            prepared.executeUpdate();
        
                                    
        }catch(Exception e){
            System.out.println("GRESKA" + e);
        }
    }
    
    public void azurirajMeni(Meni meni) throws SQLException{
       
        
        String upit = "UPDATE meni set nazivMenija= ?, predjelo= ?, glavnoJelo= ?, dezert= ?, cenaPoOsobi= ?, ketSluzbaID= ?  where meniID= ?";
          
          
          
          try{
               
              prepared = connection.prepareStatement(upit);
              prepared.setString(1, meni.getNazivMenija());
              prepared.setString(2, meni.getPredjelo());
              prepared.setString(3, meni.getGlavnoJelo());
              prepared.setString(4, meni.getDezert());
              prepared.setInt(5,meni.getCenaPoOsobi());
              prepared.setInt(6, meni.getKetSluzbaID());
              prepared.setInt(7,meni.getMeniID());
              
              System.out.println(prepared);
              prepared.executeUpdate();
          }catch(Exception ex){
              ex.printStackTrace();
          }
    }
    
       public int novMeni(){
        int novID = 0;
        try{
            this.connect();
            
            resultSet = statement.executeQuery("SELECT MAX(meniID) from meni");
            
            while(resultSet.next()){
                novID = resultSet.getInt("MAX(meniID)");
            }
            
            return novID + 1;
        }catch(Exception e){
            e.printStackTrace();
            return novID;
        }
    }
       
       public List<Meni> uzmiPoSluzbi(Keteringsluzba sluzba){
        List<Meni> meniji = new ArrayList<Meni>();
        try{
            this.connect();
            
            resultSet = statement.executeQuery("SELECT * FROM meni WHERE ketSluzbaID="+sluzba.getKetSluzbaID());
            
            while(resultSet.next()){
                meniji.add(new Meni(resultSet.getInt("meniID"), resultSet.getString("nazivMenija"), 
                             resultSet.getString("predjelo"), resultSet.getString("glavnoJelo"), resultSet.getString("dezert"),
                             resultSet.getInt("cenaPoOsobi"), resultSet.getInt("ketSluzbaID")));
            }
            
            if(meniji.size() > 0){
                return meniji;
            }
            return null;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
       
       public List<Meni> uzmiPoSluzbiID(String ID){
        List<Meni> meniji = new ArrayList<Meni>();
        try{
            this.connect();
            
            resultSet = statement.executeQuery("SELECT * FROM meni WHERE ketSluzbaID="+ ID);
            
            while(resultSet.next()){
                meniji.add(new Meni(resultSet.getInt("meniID"), resultSet.getString("nazivMenija"), 
                             resultSet.getString("predjelo"), resultSet.getString("glavnoJelo"), resultSet.getString("dezert"),
                             resultSet.getInt("cenaPoOsobi"), resultSet.getInt("ketSluzbaID")));
            }
            
            if(meniji.size() > 0){
                return meniji;
            }
            return null;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
       
          public Meni uzmiMeni(String ID){
        Meni meni = null;
        
        try{
            this.connect();
            
            resultSet = statement.executeQuery("SELECT * FROM meni where meniID=" + ID);
            
            if(resultSet.next()){
                meni = new Meni(
                              resultSet.getInt("meniID"),
                               resultSet.getString("nazivMenija"),
                               resultSet.getString("predjelo"),
                               resultSet.getString("glavnoJelo"),
                               resultSet.getString("dezert"),
                               resultSet.getInt("cenaPoOsobi"),
                               resultSet.getInt("ketSluzbaID"));
            }
            return meni;
        }catch(Exception e){
            e.printStackTrace();
            return meni;
        }
    }
          
          
              
    public void obrisiMeni(String ID){
        try{
            this.connect();
            statement.executeUpdate("DELETE FROM meni WHERE meniID=" + ID);
        }catch(Exception e){
            e.fillInStackTrace();
        }
    }
       
       
       
}
