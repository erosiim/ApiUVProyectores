package com.org.miuv;

import com.org.models.TipoUsuario;
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
public class DaoTipoUsuario implements IDao<TipoUsuario>{
    
    private transient  Connection driverPostgres;
    private boolean successQuery = false;
    private transient  PreparedStatement preQuery;

    public DaoTipoUsuario() {
        driverPostgres = ConnectionToDb.getInstance().getDriver();
    }
    
    private String getStatement(int statementOption){
        String[] statements = new String[]{
                            "INSERT INTO tipos_usuarios  VALUES (?,?);",
                            "DELETE FROM tipos_usuarios WHERE (id_tipos_usuario = ?);",
                            "UPDATE tipos_usuarios SET tipo = ? WHERE (id_tipos_usuario = ?);",
                            "SELECT * FROM tipos_usuarios WHERE (id_tipos_usuario = ?);",
                            "SELECT * FROM tipos_usuarios;"};
            return statements[statementOption];
        }
    
    public boolean updateTable(String statement,int statementOption, String[] values) {
        try {
            preQuery = driverPostgres.prepareStatement(statement);
            switch(statementOption){
                case 0:
                    preQuery.setInt(1, Integer.valueOf(values[0]));
                    preQuery.setString(2, values[1]);
                    break;
                case 1:
                    preQuery.setInt(1, Integer.valueOf(values[0]));
                    break;
                case 2:
                    preQuery.setString(1,values[1]);
                    preQuery.setInt(2,Integer.parseInt(values[0]));
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
    public boolean insertRecord(TipoUsuario t) {
        String values [] = {String.valueOf(t.getIdTipoUsuario()), t.getTipo()};
        return updateTable(getStatement(0), 0, values);
    }

    @Override
    public boolean deleteRecord(TipoUsuario t) {
        String values [] = {String.valueOf(t.getIdTipoUsuario())};
        return updateTable(getStatement(1), 1, values);
    }

    @Override
    public boolean updateRecord(TipoUsuario t) {
        String values [] = {String.valueOf(t.getIdTipoUsuario()), t.getTipo()};
        return updateTable(getStatement(2), 2, values);
    }

    @Override
    public List<TipoUsuario> getRecords() {
        List<TipoUsuario> miListaTipoUsuario = new ArrayList();
        ResultSet data = getData(getStatement(4), 4, 0);
        try {
            while(data.next()){
                TipoUsuario tipo = new TipoUsuario();
                tipo.setIdTipoUsuario(data.getInt(1));
                tipo.setTipo(data.getString(2));
                miListaTipoUsuario.add(tipo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoTipoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return miListaTipoUsuario;
    }

    @Override
    public TipoUsuario getOneRecord(TipoUsuario t) {
       ResultSet data = getData(getStatement(3), 3, t.getIdTipoUsuario());
        try {
            while(data.next()){
                t.setIdTipoUsuario(data.getInt(1));
                t.setTipo(data.getString(2));
            }} catch (SQLException ex) {
            Logger.getLogger(DaoTipoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t;
    }
    
    
}
