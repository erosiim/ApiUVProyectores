package com.org.miuv;

import com.org.models.TipoEquipo;
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
@Path("tipoEquipos")
public class TipoEquipoResource {
    private IDao daoTipoEquipo;
    private TipoEquipo tipoEquipo;
    private final String succesMessage = "OK";
    private final String errorMessage = "FAIL";


    @Context
    private UriInfo context;

    /**
     * Creates a new instance of TipoEquipoResource
     */
    public TipoEquipoResource() {
        daoTipoEquipo = new DaoTipoEquipos();
        tipoEquipo = new TipoEquipo();
    }

    /**
     * Retrieves representation of an instance of com.org.miuv.TipoEquipoResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<TipoEquipo> getAll() {
         return daoTipoEquipo.getRecords();
    }
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public TipoEquipo getOneByID(@PathParam("id")String id) {
        tipoEquipo.setIdTipoEquipo(id);
        return (TipoEquipo)daoTipoEquipo.getOneRecord(tipoEquipo);
        
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public String update(TipoEquipo tipoEquipo) {
        if(daoTipoEquipo.updateRecord(tipoEquipo))
            return succesMessage;
        else
            return errorMessage;
        
    }
@POST
@Consumes(MediaType.APPLICATION_JSON)
public String insert(TipoEquipo tipoEquipo){
        if (daoTipoEquipo.insertRecord(tipoEquipo))
            return succesMessage;
        else
            return errorMessage;
}
@DELETE
@Consumes(MediaType.APPLICATION_JSON)
@Path("/{id}")
    public String deleteOne(@PathParam("id") String id){
        tipoEquipo.setIdTipoEquipo(id);
        if(daoTipoEquipo.deleteRecord(tipoEquipo))
            return succesMessage;
        else
            return errorMessage;
    }
}
