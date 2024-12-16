/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class DbConnect {
    private static final String host="127.0.0.1";
    private static final int port=3306;
    private static final String database_name="storagedb";
    private static final String name="root";
    private static final String password="P4r0l4m34mysql";
    private static final String dataConn="jdbc:mysql://localhost:3306/storagedb";
    private static  Connection connection;
    
    public static Connection getConnect(){
        
        try {  
            connection=DriverManager.getConnection(String.format("jdbc:mysql://localhost:3306/storagedb",host,port,database_name),name,password);
        } catch (SQLException ex) {
            Logger.getLogger(DbConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return connection;
    }
    
}
