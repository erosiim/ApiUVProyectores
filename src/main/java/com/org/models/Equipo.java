package com.org.models;

/**
 *
 * @author alsorc
 */
public class Equipo {
    private int idEquipo;
    private int idTipoEquipo;
    private String serial;
    private String estado;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }

    public int getIdTipoEquipo() {
        return idTipoEquipo;
    }

    public void setIdTipoEquipo(int idTipoEquipo) {
        this.idTipoEquipo = idTipoEquipo;
    }


   

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }
    
    
    
}
