package com.org.models;

/**
 *
 * @author alsorc
 */
public class Apartado {
    private int idApartado;
    private String matricula;
    private int idEquipo;
    private int idLugar;
    private String grupo;
    private String fecha;
    private String horaInicio;
    private String horaFinal;
    private int codigoConfirmacion;
    private int codigoDevolucion;
    private String estado;

    public int getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }

    public int getIdLugar() {
        return idLugar;
    }

    public void setIdLugar(int idLugar) {
        this.idLugar = idLugar;
    }

    public int getCodigoConfirmacion() {
        return codigoConfirmacion;
    }

    public void setCodigoConfirmacion(int codigoConfirmacion) {
        this.codigoConfirmacion = codigoConfirmacion;
    }

    public int getCodigoDevolucion() {
        return codigoDevolucion;
    }

    public void setCodigoDevolucion(int codigoDevolucion) {
        this.codigoDevolucion = codigoDevolucion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    

    public int getIdApartado() {
        return idApartado;
    }

    public void setIdApartado(int idApartado) {
        this.idApartado = idApartado;
    }
   

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(String horaFinal) {
        this.horaFinal = horaFinal;
    }
    
    
    
    
}
