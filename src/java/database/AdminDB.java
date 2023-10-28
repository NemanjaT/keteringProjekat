/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import models.Admin;

/**
 *
 * @author Nemanja
 */
public class AdminDB {
    String url = "jdbc:mysql://localhost:3306/ketering";
    String username = "root";
    String password = "";
    
    Connection connection;
    Statement statement;
    ResultSet resultSet;
    
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
    
       public Admin validacijaAdmina(String username, String password) throws SQLException
    {
        try{
            this.connect();
        }catch(Exception e){
            System.out.println("Greska!" + e);
        }
        Admin admin = null; 
        
        resultSet = statement.executeQuery("SELECT * from admin where username='"+username+"' and password='"+password+"'");
        
        if(resultSet.next()){
            admin = new Admin(resultSet.getString("username"),resultSet.getString("password"));
        }
        
        return admin;
    }
}
