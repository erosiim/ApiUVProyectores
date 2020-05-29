package com.org.miuv;


import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
/*Autor: Carlos Alonso Escamilla Rocafuerte
*/
public class ConnectionToDb implements Serializable{
    
    //Variable de instancia de clase única para implementar patrón singleton
    private static ConnectionToDb connectToDb;
    //Variable para gestionar la conexión
    private transient  Connection driverPostgres;
    
    private transient  PreparedStatement preSentencia;
   
    private boolean successQuery = false;
    
    private transient  PreparedStatement preQuery;
    
    
    //Constructor privado con las credenciales para cceder a la base de datos
    private ConnectionToDb() {        
        try {
            Class.forName("org.postgresql.Driver");
            driverPostgres = DriverManager.getConnection("jdbc:postgresql://192.168.1.74:7000/myaplicationuv",
                    "postgres", "12334");
            Logger.getLogger(ConnectionToDb.class.getName()).log(Level.INFO, "Me conecté papu");
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ConnectionToDb.class.getName()).log(Level.SEVERE, null, ex);
        }            
    }
    
    //Implementación patrón Singleton
    public static ConnectionToDb getInstance(){
        if(connectToDb == null)
            connectToDb = new ConnectionToDb();
        return connectToDb;
    }
    
    public Connection getDriver(){
        return driverPostgres;
    }
    
    
    

}