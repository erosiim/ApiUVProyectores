package com.org.miuv;
import com.org.models.TipoUsuario;
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
@Path("tipoUsuarios")
public class TipoUsuarioResource {
    private IDao daoTipoUsuario;
    private TipoUsuario tipoUsuario;
    private final String succesMessage = "OK";
    private final String errorMessage = "FAIL";

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of TipoUsuarioResource
     */
    public TipoUsuarioResource() {
        daoTipoUsuario = new DaoTipoUsuario();
        tipoUsuario= new TipoUsuario();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<TipoUsuario> getAll() {
         return daoTipoUsuario.getRecords();
    }
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public TipoUsuario getOneByID(@PathParam("id")String id) {
        tipoUsuario.setIdTipoUsuario(id);
        return (TipoUsuario)daoTipoUsuario.getOneRecord(tipoUsuario);
        
    }

    /**
     * PUT method for updating or creating an instance of CarrerasResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public String update(TipoUsuario tipoUsuario) {
        if(daoTipoUsuario.updateRecord(tipoUsuario))
            return succesMessage;
        else
            return errorMessage;
        
    }
@POST
@Consumes(MediaType.APPLICATION_JSON)
public String insert(TipoUsuario tipoUsuario){
        if (daoTipoUsuario.insertRecord(tipoUsuario))
            return succesMessage;
        else
            return errorMessage;
}
@DELETE
@Consumes(MediaType.APPLICATION_JSON)
@Path("/{id}")
    public String deleteOne(@PathParam("id") String id){
        tipoUsuario.setIdTipoUsuario(id);
        if(daoTipoUsuario.deleteRecord(tipoUsuario))
            return succesMessage;
        else
            return errorMessage;
    }
}
