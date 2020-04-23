package com.org.miuv;

import com.org.models.Carrera;
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
public class DaoCarreras implements  IDao<Carrera>{
    
    private transient  Connection driverPostgres;
    private boolean successQuery = false;
    private transient  PreparedStatement preQuery;

    public DaoCarreras() {
        driverPostgres = ConnectionToDb.getInstance().getDriver();
    }

        private String getStatement(int statementOption){
        String[] statements = new String[]{
                            "INSERT INTO carreras  VALUES (?,?);",
                            "DELETE FROM carreras WHERE (id_carrera= ?);",
                            "UPDATE carreras SET nombre = ? WHERE (id_carrera = ?);",
                            "SELECT * FROM carreras WHERE (id_carrera = ?);",
                            "SELECT * FROM carreras;"};

            return statements[statementOption];
        }

        public boolean updateTable(String statement,int statementOption, String[] values) {
        try {
            preQuery = driverPostgres.prepareStatement(statement);
            switch(statementOption){
                case 0:
                    preQuery.setString(1, values[0]);
                    preQuery.setString(2, values[1]);
                    break;
                case 1:
                    preQuery.setString(1, values[0]);
                    break;
                case 2:
                    preQuery.setString(1,values[1]);
                    preQuery.setString(2,values[0]);
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
    public boolean insertRecord(Carrera t) {
        String values [] = {t.getIdCarrera(), t.getNombre()};
        return updateTable(getStatement(0),0 , values);
    }

    @Override
    public boolean deleteRecord(Carrera t) {
        String values [] = {t.getIdCarrera()};
        return updateTable(getStatement(1),1 , values);
    }

    @Override
    public boolean updateRecord(Carrera t) {
        String values [] = {t.getIdCarrera(), t.getNombre()};
        return updateTable(getStatement(2), 2, values);
    }

    @Override
    public List<Carrera> getRecords() {
        List<Carrera> listaCarreras = new ArrayList();
        ResultSet data = getData(getStatement(4), 4, "");
        try{
            while(data.next()){
                Carrera carrera = new Carrera();
                carrera.setIdCarrera(data.getString(1));
                carrera.setNombre(data.getString(2));
                listaCarreras.add(carrera);
                System.err.println(carrera.getIdCarrera() + " " + carrera.getNombre());
            }
        }catch(SQLException ex){
            Logger.getLogger(DaoCarreras.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaCarreras;
    }

    @Override
    public Carrera getOneRecord(Carrera t) {
        ResultSet data = getData(getStatement(3), 3, t.getIdCarrera());
        try{
            if(data.next()){
                t.setIdCarrera(data.getString(1));
                t.setNombre(data.getString(2));
                System.err.println(t.getIdCarrera() + " " + t.getNombre());
            }
        }catch(SQLException ex){
            Logger.getLogger(DaoCarreras.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t;
    }
     
}
