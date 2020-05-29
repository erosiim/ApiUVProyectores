package com.org.miuv;


import com.org.models.Usuario;
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
@Path("usuarios")
public class UsuariosResource {
    private IDao daoUsuario;
    private Usuario usuario;
    private final String succesMessage = "OK";
    private final String errorMessage = "FAIL";

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of UsuariosResource
     */
    public UsuariosResource() {
        usuario = new Usuario();
        daoUsuario = new DaoUsuarios();
    }

        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public List<Usuario> getAll() {
             return daoUsuario.getRecords();
        }

        @GET
        @Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
        @Path("{id}")
        public Usuario getOneByID(@PathParam("id")String id) {
            usuario.setMatricula(id);
            return (Usuario)daoUsuario.getOneRecord(usuario);  
        }

        /**
         * PUT method for updating or creating an instance of CarrerasResource
         * @param content representation for the resource
         */
        @PUT
        @Consumes(MediaType.APPLICATION_JSON)
        public String update(Usuario usuario) {
            if(daoUsuario.updateRecord(usuario))
                return succesMessage;
            else
                return errorMessage;

        }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String insert(Usuario usuario){
            if (daoUsuario.insertRecord(usuario))
                return succesMessage;
            else
                return errorMessage;
    }
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
        public String deleteOne(@PathParam("id") String id){
            usuario.setMatricula(id);
            if(daoUsuario.deleteRecord(usuario))
                return succesMessage;
            else
                return errorMessage;
        }
}
