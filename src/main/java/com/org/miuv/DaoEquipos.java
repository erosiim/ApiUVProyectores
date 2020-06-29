package com.org.miuv;

import com.org.models.Equipo;
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
public class DaoEquipos implements IDao<Equipo>{

     private transient  Connection driverPostgres;
    private boolean successQuery = false;
    private transient  PreparedStatement preQuery;

    public DaoEquipos() {
        driverPostgres = ConnectionToDb.getInstance().getDriver();
    }
    
    private String getStatement(int statementOption){
        String[] statements = new String[]{
                            "INSERT INTO equipos VALUES (?,?,?);",
                            "DELETE FROM equipos WHERE (id_equipo= ?);",
                            "UPDATE equipos SET id_tipos_equipo = ?, serial = ?, estado = ? WHERE (id_equipo = ?);",
                            "SELECT * FROM equipos WHERE (id_equipo = ?);",
                            "SELECT * FROM equipos;"};
            return statements[statementOption];
        }
    public boolean updateTable(String statement,int statementOption, String[] values) {
        try {
            preQuery = driverPostgres.prepareStatement(statement);
            switch(statementOption){
                case 0:
                    preQuery.setInt(1, Integer.parseInt(values[0]));
                    preQuery.setInt(2, Integer.parseInt(values[1]));
                    preQuery.setString(3, values[2]);
                    break;
                case 1:
                    preQuery.setInt(1, Integer.parseInt(values[0]));
                    break;
                case 2:
                    preQuery.setInt(1,Integer.parseInt(values[1]));
                    preQuery.setString(2,values[2]);
                    preQuery.setString(3,values[3]);
                    preQuery.setInt(4, Integer.parseInt(values[0]));
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
    
    public ResultSet getData(String statement, int statementOption,int id) {
        ResultSet data = null;
        try {
            preQuery = driverPostgres.prepareStatement(statement);
            
            if ( statementOption == 3)
                preQuery.setInt(1, id);
            
            data = preQuery.executeQuery();            
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionToDb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }
    
    
    @Override
    public boolean insertRecord(Equipo t) {
        String values [] = {String.valueOf(t.getIdEquipo()), String.valueOf(t.getIdTipoEquipo()), t.getSerial()};
        return updateTable(getStatement(0), 0, values);
    }

    @Override
    public boolean deleteRecord(Equipo t) {
        String values [] = {String.valueOf(t.getIdEquipo())};
        return updateTable(getStatement(1), 1, values);
    }

    @Override
    public boolean updateRecord(Equipo t) {
        String values [] = {String.valueOf(t.getIdEquipo()), String.valueOf(t.getIdTipoEquipo()), t.getSerial(), t.getEstado()};
        return updateTable(getStatement(2), 2, values);
    }

    @Override
    public List<Equipo> getRecords() {
        List<Equipo> listaEquipos = new ArrayList();
        ResultSet data = getData(getStatement(4), 4, 0);
         try {
             while(data.next()){
                 Equipo equipo = new Equipo();
                 equipo.setIdEquipo(data.getInt(1));
                 equipo.setIdTipoEquipo(data.getInt(2));
                 equipo.setSerial(data.getString(3));
                 equipo.setEstado(data.getString(4));
                 listaEquipos.add(equipo);
             }} catch (SQLException ex) {
             Logger.getLogger(DaoEquipos.class.getName()).log(Level.SEVERE, null, ex);
         }
        return listaEquipos;
    }

    @Override
    public Equipo getOneRecord(Equipo t) {
        ResultSet data = getData(getStatement(3), 3, t.getIdEquipo());
         try {
             if(data.next()){
                 t.setIdEquipo(data.getInt(1));
                 t.setIdTipoEquipo(data.getInt(2));
                 t.setSerial(data.getString(3));
             }} catch (SQLException ex) {
             Logger.getLogger(DaoEquipos.class.getName()).log(Level.SEVERE, null, ex);
         }
        return t;
    }
    
}
