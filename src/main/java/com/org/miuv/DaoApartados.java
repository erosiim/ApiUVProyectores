package com.org.miuv;

import com.org.models.Apartado;
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
public class DaoApartados implements IDao<Apartado>{

    private transient  Connection driverPostgres;
    private boolean successQuery = false;
    private transient  PreparedStatement preQuery;
    private long key = -1L;
    public DaoApartados() {
        driverPostgres = ConnectionToDb.getInstance().getDriver();
    }
    
    private String getStatement(int statementOption){
        String[] statements = new String[]{
                            "INSERT INTO apartados (matricula, id_equipo, id_lugar, grupo, fecha, hora_inicio, hora_final, codigo_confirmacion, codigo_devolucion, estado) VALUES (?,?,?,?,?,?,?,?,?,?);",
                            "DELETE FROM apartados WHERE (id_apartado = ?);",
                            "UPDATE apartados SET matricula = ?, id_equipo = ?, id_lugar = ?, grupo = ?,"
                        +   "fecha =?, hora_inicio = ?, hora_final = ?, codigo_confirmacion = ?, codigo_devolucion = ?, estado = ?  WHERE (id_apartado = ?);",
                            "SELECT * FROM apartados WHERE (id_apartado = ?);",
                            "SELECT * FROM apartados;"};
            return statements[statementOption];
     }
    
    public boolean updateTable(String statement,int statementOption, String[] values) {
        try {
            preQuery = driverPostgres.prepareStatement(statement);
            switch(statementOption){
                case 0:
                    preQuery.setString(1, values[0]);
                    preQuery.setInt(2, Integer.parseInt(values[1]));
                    preQuery.setInt(3, Integer.parseInt(values[2]));
                    preQuery.setString(4, values[3]);
                    preQuery.setDate(5, java.sql.Date.valueOf(values[4]));
                    //preQuery.setDate(5, java.sql.Date.valueOf("2017-09-24"));
                    preQuery.setTime(6, java.sql.Time.valueOf(values[5]));
                    //preQuery.setTime(6, java.sql.Time.valueOf("08:00:00"));
                    preQuery.setTime(7, java.sql.Time.valueOf(values[6]));
                    //preQuery.setTime(7, java.sql.Time.valueOf("08:00:00"));
                    preQuery.setInt(8, Integer.parseInt(values[7]));
                    preQuery.setInt(9, Integer.parseInt(values[8]));
                    preQuery.setString(10, values[9]);
                    //preQuery.setString(8, values[7]);
                    break;
                case 1:
                    preQuery.setInt(1, Integer.parseInt(values[0]));
                    break;
                case 2:
                    preQuery.setString(1,values[1]);
                    preQuery.setInt(2,Integer.parseInt(values[2]));
                    preQuery.setInt(3,Integer.parseInt(values[3]));
                    preQuery.setString(4,values[4]);
                    preQuery.setString(5,values[5]);
                    preQuery.setString(6,values[6]);
                    preQuery.setString(7,values[7]);
                    preQuery.setInt(8, Integer.parseInt(values[7]));
                    preQuery.setInt(9, Integer.parseInt(values[7]));
                    preQuery.setString(10,values[7]);
                    preQuery.setInt(11,Integer.parseInt(values[0]));
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
    
    public long updateTableAndGetId(String statement,int statementOption, String[] values) {
        try {
            preQuery = driverPostgres.prepareStatement(statement, PreparedStatement.RETURN_GENERATED_KEYS);
            switch(statementOption){
                case 0:
                    preQuery.setString(1, values[0]);
                    preQuery.setInt(2, Integer.parseInt(values[1]));
                    preQuery.setInt(3, Integer.parseInt(values[2]));
                    preQuery.setString(4, values[3]);
                    preQuery.setDate(5, java.sql.Date.valueOf(values[4]));
                    //preQuery.setDate(5, java.sql.Date.valueOf("2017-09-24"));
                    preQuery.setTime(6, java.sql.Time.valueOf(values[5]));
                    //preQuery.setTime(6, java.sql.Time.valueOf("08:00:00"));
                    preQuery.setTime(7, java.sql.Time.valueOf(values[6]));
                    //preQuery.setTime(7, java.sql.Time.valueOf("08:00:00"));
                    preQuery.setInt(8, Integer.parseInt(values[7]));
                    preQuery.setInt(9, Integer.parseInt(values[8]));
                    preQuery.setString(10, values[9]);
                    //preQuery.setString(8, values[7]);
                    break;
                case 1:
                    preQuery.setInt(1, Integer.parseInt(values[0]));
                    break;
                case 2:
                    preQuery.setString(1,values[1]);
                    preQuery.setInt(2,Integer.parseInt(values[2]));
                    preQuery.setInt(3,Integer.parseInt(values[3]));
                    preQuery.setString(4,values[4]);
                    preQuery.setString(5,values[5]);
                    preQuery.setString(6,values[6]);
                    preQuery.setString(7,values[7]);
                    preQuery.setInt(8, Integer.parseInt(values[7]));
                    preQuery.setInt(9, Integer.parseInt(values[7]));
                    preQuery.setString(10,values[7]);
                    preQuery.setInt(11,Integer.parseInt(values[0]));
                    break;
                default:
                    System.err.println("No elegiste una opción válida");
            }
            if(preQuery.executeUpdate()>0)
                successQuery = true;
            
            ResultSet rs = preQuery.getGeneratedKeys();  
            key = rs.next() ? rs.getInt(1) : 0;
            if(key!=0){
                System.out.println("Generated key="+key);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionToDb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return key;
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
    
    public long insertRecordAndGetId(Apartado t) {
        String values [] = { t.getMatricula(), String.valueOf(t.getIdEquipo()), String.valueOf(t.getIdLugar()), t.getGrupo(), t.getFecha(), t.getHoraInicio(), t.getHoraFinal(),  String.valueOf(t.getCodigoConfirmacion()), String.valueOf(t.getCodigoDevolucion()), t.getEstado()};
        return updateTableAndGetId(getStatement(0), 0, values);
    }
     
    @Override
    public boolean insertRecord(Apartado t) {
        String values [] = { t.getMatricula(), String.valueOf(t.getIdEquipo()), String.valueOf(t.getIdLugar()), t.getGrupo(), t.getFecha(), t.getHoraInicio(), t.getHoraFinal(),  String.valueOf(t.getCodigoConfirmacion()), String.valueOf(t.getCodigoDevolucion()), t.getEstado()};
        return updateTable(getStatement(0), 0, values);
    }

    @Override
    public boolean deleteRecord(Apartado t) {
        String values [] = {String.valueOf(t.getIdApartado())};
        return updateTable(getStatement(1), 1, values);
    }

    @Override
    public boolean updateRecord(Apartado t) {
        String values [] = {String.valueOf(t.getIdApartado()), t.getMatricula(), String.valueOf(t.getIdEquipo()), String.valueOf(t.getIdLugar()), t.getGrupo(), t.getFecha(), t.getHoraInicio(), t.getHoraFinal(),  String.valueOf(t.getCodigoConfirmacion()), String.valueOf(t.getCodigoDevolucion()), t.getEstado() };
        return updateTable(getStatement(2), 2, values);
    }

    @Override
    public List<Apartado> getRecords() {
        List<Apartado> listaApartados = new ArrayList();
        ResultSet data = getData(getStatement(4), 4, 0);
        try {
            while(data.next()){
                Apartado apartado = new Apartado();
                apartado.setIdApartado(data.getInt(1));
                apartado.setMatricula(data.getString(2));
                apartado.setIdEquipo(data.getInt(3));
                apartado.setIdLugar(data.getInt(4));
                apartado.setGrupo(data.getString(5));
                apartado.setFecha(data.getString(6));
                apartado.setHoraInicio(data.getString(7));
                apartado.setHoraFinal(data.getString(8));
                apartado.setEstado(data.getString(9));
                listaApartados.add(apartado);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoApartados.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaApartados;
    }

    @Override
    public Apartado getOneRecord(Apartado t) {
        ResultSet data = getData(getStatement(3), 3, t.getIdApartado());
        try {
            if(data.next()){
                 t.setIdApartado(data.getInt(1));
                t.setMatricula(data.getString(2));
                t.setIdEquipo(data.getInt(3));
                t.setIdLugar(data.getInt(4));
                t.setGrupo(data.getString(5));
                t.setFecha(data.getString(6));
                t.setHoraInicio(data.getString(7));
                t.setHoraFinal(data.getString(8));
                t.setEstado(data.getString(9));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoApartados.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t;
    }
    
}
