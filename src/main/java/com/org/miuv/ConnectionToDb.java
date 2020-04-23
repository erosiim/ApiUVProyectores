package com.org.miuv;


import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
/*Autor: Carlos Alonso Escamilla Rocafuerte
/*Fecha de modificación: 05/Marzo/2020
/*Descripción: Clase conexión a una base de datos de nombre empleados que se encuentra 
/*en un contenedor, mismo que está ligado através del puerto 7000 del host, se implementa 
/*patrón singleton y se incluyen dos métodos para ejecutar sentencias sql.
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
            driverPostgres = DriverManager.getConnection("jdbc:postgresql://localhost/myaplicationuv",
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