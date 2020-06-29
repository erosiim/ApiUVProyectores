/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.miuv;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import com.org.models.*;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
/**
 * REST Web Service
 *
 * @author alsorc
 */
@Path("carreras")
public class CarrerasResource {
    private IDao daoCarrera;
    private Carrera carrera;
    private final String succesMessage = "OK";
    private final String errorMessage = "FAIL";
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CarrerasResource
     */
    public CarrerasResource() {
        daoCarrera = new DaoCarreras();
        carrera = new Carrera();
    }

    /**
     * Retrieves representation of an instance of com.org.miuv.CarrerasResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Carrera> getAll() {
         return daoCarrera.getRecords();
    }
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Carrera getOneByID(@PathParam("id")int id) {
        carrera.setIdCarrera(id);
        return (Carrera)daoCarrera.getOneRecord(carrera);
        
    }

    /**
     * PUT method for updating or creating an instance of CarrerasResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public String update(Carrera carrera) {
        if(daoCarrera.updateRecord(carrera))
            return succesMessage;
        else
            return errorMessage;
        
    }
@POST
@Consumes(MediaType.APPLICATION_JSON)
public String insert(Carrera carrera){
        if (daoCarrera.insertRecord(carrera))
            return succesMessage;
        else
            return errorMessage;
}
@DELETE
@Consumes(MediaType.APPLICATION_JSON)
@Path("{id}")
    public String deleteOne(@PathParam("id") int id){
        carrera.setIdCarrera(id);
        if(daoCarrera.deleteRecord(carrera))
            return succesMessage;
        else
            return errorMessage;
    }
}
