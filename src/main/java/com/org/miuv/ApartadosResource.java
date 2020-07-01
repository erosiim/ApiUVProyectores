package com.org.miuv;

import com.org.models.Apartado;
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
@Path("apartados")
public class ApartadosResource {
    private IDao daoApartado;
    private Apartado apartado;
    private final String succesMessage = "OK";
    private final String errorMessage = "FAIL";
    private DaoApartados myDaoA;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ApartadosResource
     */
    public ApartadosResource() {
        daoApartado = new DaoApartados();
        apartado = new Apartado();
        myDaoA = new DaoApartados();
    }

   @GET
        @Produces(MediaType.APPLICATION_JSON)
        public List<Apartado> getAll() {
             return daoApartado.getRecords();
        }

        @GET
        @Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
        @Path("{id}")
        public Apartado getOneByID(@PathParam("id")int id) {
            apartado.setIdApartado(id);
            return (Apartado)daoApartado.getOneRecord(apartado);  
        }

        /**
         * PUT method for updating or creating an instance of CarrerasResource
         * @param content representation for the resource
         */
        @PUT
        @Consumes(MediaType.APPLICATION_JSON)
        public String update(Apartado apartado) {
            if(daoApartado.updateRecord(apartado))
                return succesMessage;
            else
                return errorMessage;

        }
//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    public String insert(Apartado apartado){
//            if (daoApartado.insertRecord(apartado))
//                return succesMessage;
//            else
//                return errorMessage;
//    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public long insert(Apartado apartado){
        return myDaoA.insertRecordAndGetId(apartado);
    }
    
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
        public String deleteOne(@PathParam("id") int id){
            apartado.setIdApartado(id);
            if(daoApartado.deleteRecord(apartado))
                return succesMessage;
            else
                return errorMessage;
        }
}
