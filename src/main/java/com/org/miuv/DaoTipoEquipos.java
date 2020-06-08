package com.org.miuv;

import com.org.models.TipoEquipo;
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
public class DaoTipoEquipos implements IDao<TipoEquipo>{

    private transient  Connection driverPostgres;
    private boolean successQuery = false;
    private transient  PreparedStatement preQuery;
    
    public DaoTipoEquipos() {
        driverPostgres = ConnectionToDb.getInstance().getDriver();
    }
    
    private String getStatement(int statementOption){
       String[] statements = new String[]{
                           "INSERT INTO tipos_equipos  VALUES (?,?,?);",
                           "DELETE FROM tipos_equipos WHERE (id_tipos_quipo = ?);",
                           "UPDATE tipos_equipos SET nombre = ?, entrada = ? WHERE (id_tipos_quipo = ?);",
                           "SELECT * FROM tipos_equipos WHERE (id_tipos_quipo = ?);",
                           "SELECT * FROM tipos_equipos;"};
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
                    preQuery.setString(3, values[0]);
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
    public boolean insertRecord(TipoEquipo t) {
        String values[] = {t.getIdTipoEquipo(), t.getNombre(), t.getEntrada()};
        return updateTable(getStatement(0), 0, values);
    }

    @Override
    public boolean deleteRecord(TipoEquipo t) {
        String values [] = {t.getIdTipoEquipo()};
        return updateTable(getStatement(1), 1, values);
    }

    @Override
    public boolean updateRecord(TipoEquipo t) {
        String values[] = {t.getIdTipoEquipo(), t.getNombre(), t.getEntrada()};
        return updateTable(getStatement(2), 2, values);
    }

    @Override
    public List<TipoEquipo> getRecords() {
        List<TipoEquipo> listaTipoEquipos = new ArrayList();
        ResultSet data = getData(getStatement(4), 4, "");
        try {
            while(data.next()){
                TipoEquipo tipoEquipo = new TipoEquipo();
                tipoEquipo.setIdTipoEquipo(data.getString(1));
                tipoEquipo.setNombre(data.getString(2));
                tipoEquipo.setEntrada(data.getString(3));
                listaTipoEquipos.add(tipoEquipo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoTipoEquipos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaTipoEquipos;
    }

    @Override
    public TipoEquipo getOneRecord(TipoEquipo t) {
        ResultSet data = getData(getStatement(3), 3, t.getIdTipoEquipo());
        try {
            if(data.next()){
                t.setIdTipoEquipo(data.getString(1));
                t.setNombre(data.getString(2));
                t.setEntrada(data.getString(3));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoTipoEquipos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t;
    }
    
}
