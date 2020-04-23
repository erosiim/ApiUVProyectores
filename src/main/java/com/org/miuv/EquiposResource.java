/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.miuv;

import com.org.models.Equipo;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author alsorc
 */
@Path("equipos")
public class EquiposResource {
     private IDao daoEquipo;
    private Equipo equipo;
    private final String succesMessage = "OK";
    private final String errorMessage = "FAIL";

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of EquiposResource
     */
    public EquiposResource() {
        daoEquipo = new  DaoEquipos();
        equipo = new Equipo();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Equipo> getAll() {
         return daoEquipo.getRecords();
    }
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Equipo getOneByID(@PathParam("id")String id) {
        equipo.setIdEquipo(id);
        return (Equipo)daoEquipo.getOneRecord(equipo);
        
    }

    /**
     * PUT method for updating or creating an instance of CarrerasResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public String update(Equipo equipo) {
        if(daoEquipo.updateRecord(equipo))
            return succesMessage;
        else
            return errorMessage;
        
    }
@POST
@Consumes(MediaType.APPLICATION_JSON)
public String insert(Equipo equipo){
        if (daoEquipo.insertRecord(equipo))
            return succesMessage;
        else
            return errorMessage;
}
@DELETE
@Consumes(MediaType.APPLICATION_JSON)
@Path("{id}")
    public String deleteOne(@PathParam("id") String id){
        equipo.setIdEquipo(id);
        if(daoEquipo.deleteRecord(equipo))
            return succesMessage;
        else
            return errorMessage;
    }
}
