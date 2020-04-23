package com.org.miuv;

import com.org.models.Lugar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alsorc
 */
public class DaoLugares implements  IDao<Lugar>{
    
    private transient  Connection driverPostgres;
    private boolean successQuery = false;
    private transient  PreparedStatement preQuery;

    public DaoLugares() {
        driverPostgres = ConnectionToDb.getInstance().getDriver();
    }
    
        private String getStatement(int statementOption){
        String[] statements = new String[]{
                            "INSERT INTO lugares VALUES (?,?,?);",
                            "DELETE FROM lugares WHERE (id_lugar = ?);",
                            "UPDATE lugares SET edificio = ?, aula = ? WHERE (id_lugar = ?);",
                            "SELECT * FROM lugares WHERE (id_lugar = ?);",
                            "SELECT * FROM lugares;"};
            return statements[statementOption];
        }
        
         public boolean updateTable(String statement,int statementOption, String[] values) {
        try {
            preQuery = driverPostgres.prepareStatement(statement);
            switch(statementOption){
                case 0:
                    preQuery.setString(1, values[0]);
                    preQuery.setString(2, values[1]);
                    preQuery.setString(3, values[2]);
                    break;
                case 1:
                    preQuery.setString(1, values[0]);
                    break;
                case 2:
                    preQuery.setString(1,values[1]);
                    preQuery.setString(2,values[2]);
                    preQuery.setString(3,values[0]);
                    break;
                default:
                    System.err.println("No elegiste una opción válida");
            }
            if(preQuery.executeUpdate()>0)
                successQuery = true;

        } catch (SQLException ex) {
            Logger.getLogger(ConnectionToDb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return successQuery;
    }
    
    public ResultSet getData(String statement, int statementOption,String id) {
        ResultSet data = null;
        try {
            preQuery = driverPostgres.prepareStatement(statement);
            
            if ( statementOption == 3)
                preQuery.setString(1, id);
            
            data = preQuery.executeQuery();            
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionToDb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }
    
    
    
    @Override
    public boolean insertRecord(Lugar t) {
        String values [] = {t.getIdLugar(), t.getEdificio(), t.getAula()};
        return updateTable(getStatement(0), 0, values);
    }

    @Override
    public boolean deleteRecord(Lugar t) {
        String values [] = {t.getIdLugar()};
        return updateTable(getStatement(1), 1, values);
    }

    @Override
    public boolean updateRecord(Lugar t) {
        String values [] = {t.getIdLugar(), t.getEdificio(), t.getAula()};
        return updateTable(getStatement(2), 2, values);
    }

    @Override
    public List<Lugar> getRecords() {
        List<Lugar> listaLugares = new ArrayList();
        ResultSet data = getData(getStatement(4), 4, "");
        try {
            while(data.next()){
                Lugar lugar = new Lugar();
                lugar.setIdLugar(data.getString(1));
                lugar.setEdificio(data.getString(2));
                lugar.setAula(data.getString(3));
                listaLugares.add(lugar);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoLugares.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaLugares;
    }

    @Override
    public Lugar getOneRecord(Lugar t) {
        ResultSet data = getData(getStatement(3), 3, t.getIdLugar());
        try {
            if(data.next()){
                t.setIdLugar(data.getString(1));
                t.setEdificio(data.getString(2));
                t.setAula(data.getString(3));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoLugares.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t;
    }
    
}
