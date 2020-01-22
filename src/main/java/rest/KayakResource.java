package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Image;
import entities.Kayak;
import entities.User;
import errorhandling.NotFoundException;
import facades.KayakFacade;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import utils.EMF_Creator;

/**
 * @author shpat
 */
@Path("kayak")
public class KayakResource {

    private static EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE);
    private static final KayakFacade FACADE = KayakFacade.getKayakFacade(EMF);
    
    
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getInfoForAll() {
        return "{\"msg\":\"Hello anonymous\"}";
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("all")
    public String getAllKayaks() throws NotFoundException {
        return GSON.toJson(FACADE.getAllKayaks());
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("bookings")
    public String getAllBookings() throws NotFoundException {
        return GSON.toJson(FACADE.getAllBookings());
    } 
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getbyspace/{number}")
    public String getKayakBySpace(@PathParam("number") int personsallowed) throws NotFoundException {
        return GSON.toJson(FACADE.getKayakBySpace(personsallowed));
    }
    
    
    @RolesAllowed("admin")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("addkayak")
    public String addKayak(String jsonString) throws NotFoundException {
        Kayak kayak = GSON.fromJson(jsonString, Kayak.class);
        return GSON.toJson(FACADE.addKayak(kayak));
    }
    
    @RolesAllowed("admin")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("addimage")
    public String addImage(String jsonString) throws NotFoundException {
        Image image = GSON.fromJson(jsonString, Image.class);
        return GSON.toJson(FACADE.addImage(image));
    }
    
    @RolesAllowed("admin")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("deletekayak/{id}")
    public String deleteKayak(@PathParam("id") Long id) throws NotFoundException {
        return GSON.toJson(FACADE.deleteKayak(id));
    }

  
    
    
}
