package com.org.models;

/**
 *
 * @author alsorc
 */
public class Equipo {
    private String idEquipo;
    private String idTipoEquipo;
    private String serial;
    private String estado;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }


    public String getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(String idEquipo) {
        this.idEquipo = idEquipo;
    }

    public String getIdTipoEquipo() {
        return idTipoEquipo;
    }

    public void setIdTipoEquipo(String idTipoEquipo) {
        this.idTipoEquipo = idTipoEquipo;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }
    
    
    
}
