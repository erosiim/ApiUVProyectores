package com.org.miuv;

import com.google.common.hash.Hashing;
import com.org.models.Usuario;
import java.nio.charset.StandardCharsets;
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
public class DaoUsuarios implements IDao<Usuario>{
    private transient  Connection driverPostgres;
    private boolean successQuery = false;
    private transient  PreparedStatement preQuery;
    
    
    private  String encryptPasword(String pass){
        String sha256hex = Hashing.sha256()
                .hashString(pass, StandardCharsets.UTF_8)
                .toString();
        return sha256hex;
    }
    
    

    public DaoUsuarios() {
        driverPostgres = ConnectionToDb.getInstance().getDriver();
    }
    
    private String getStatement(int statementOption){
        String[] statements = new String[]{
                            "INSERT INTO usuarios  VALUES (?,?,?,?,?,?,?,?);",
                            "DELETE FROM usuarios WHERE (matricula = ?);",
                            "UPDATE usuarios SET id_tipos_usuario = ?, id_carrera = ?, nombre = ?, apellido_paterno = ?,"
                            + "apellido_materno =?, contrasena = ?, grupo = ? WHERE (matricula = ?);",
                            "SELECT * FROM usuarios WHERE (matricula = ?);",
                            "SELECT * FROM usuarios;"};
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
                    preQuery.setString(4, values[3]);
                    preQuery.setString(5, values[4]);
                    preQuery.setString(6, values[5]);
                    preQuery.setString(7, values[6]);
                    preQuery.setString(8, values[7]);
                    break;
                case 1:
                    preQuery.setString(1, values[0]);
                    break;
                case 2:
                    preQuery.setString(1,values[1]);
                    preQuery.setString(2,values[2]);
                    preQuery.setString(3,values[3]);
                    preQuery.setString(4,values[4]);
                    preQuery.setString(5,values[5]);
                    preQuery.setString(6,values[6]);
                    preQuery.setString(7,values[6]);
                    preQuery.setString(8,values[0]);
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
    public boolean insertRecord(Usuario t) {
        String values[] = {t.getMatricula(), t.getIdTipoUsuario(), t.getIdCarrera(), t.getNombre(), t.getApellidoPaterno(), t.getApellidoMaterno(), encryptPasword(t.getContrasena()), t.getGrupo()};
        return updateTable(getStatement(0), 0, values);
    }

    @Override
    public boolean deleteRecord(Usuario t) {
        String values[] = {t.getMatricula()};
        return updateTable(getStatement(1), 1, values);
    }

    @Override
    public boolean updateRecord(Usuario t) {
        String values[] = {t.getMatricula(), t.getIdTipoUsuario(), t.getIdCarrera(), t.getNombre(), t.getApellidoPaterno(), t.getApellidoMaterno(), t.getContrasena(), t.getGrupo()};
        return updateTable(getStatement(2), 2, values);
    }

    @Override
    public List<Usuario> getRecords() {
        List<Usuario> listaUsuarios = new ArrayList();
        ResultSet data = getData(getStatement(4), 4, "");
        try {
            while(data.next()){
                Usuario usuario = new Usuario();
                usuario.setMatricula(data.getString(1));
                usuario.setIdTipoUsuario(data.getString(2));
                usuario.setIdCarrera(data.getString(3));
                usuario.setNombre(data.getString(4));
                usuario.setApellidoPaterno(data.getString(5));
                usuario.setApellidoMaterno(data.getString(6));
                usuario.setContrasena(data.getString(7));
                usuario.setGrupo(data.getString(8));
                listaUsuarios.add(usuario);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaUsuarios;
    }

    @Override
    public Usuario getOneRecord(Usuario t) {
        ResultSet data = getData(getStatement(3), 3, t.getMatricula());
        try {
            if(data.next()){
                t.setMatricula(data.getString(1));
                t.setIdTipoUsuario(data.getString(2));
                t.setIdCarrera(data.getString(3));
                t.setNombre(data.getString(4));
                t.setApellidoPaterno(data.getString(5));
                t.setApellidoMaterno(data.getString(6));
                t.setContrasena(data.getString(7));
                t.setGrupo(data.getString(8));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return t;
    }
    
}
