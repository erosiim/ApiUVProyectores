package com.org.models;

/**
 *
 * @author alsorc
 */
public class TipoEquipo {
    private String idTipoEquipo;
    private String nombre;
    private String entrada;
    private String estado;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getIdTipoEquipo() {
        return idTipoEquipo;
    }

    public void setIdTipoEquipo(String idTipoEquipo) {
        this.idTipoEquipo = idTipoEquipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEntrada() {
        return entrada;
    }

    public void setEntrada(String entrada) {
        this.entrada = entrada;
    }
    
    
    
}
