package jee.training.registration.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jee.training.registration.dao.EventDao;
import jee.training.registration.model.Event;

import java.util.List;

@Path("/events")
@Produces("application/json")
public class EventResource {

    @Inject
    private EventDao eventDao;

    @GET
    public List<Event> findAll() {
        return eventDao.findAll();
    }

    @GET
    @Path("/{id}")
    public Event find(@PathParam("id") String id) {
        return eventDao.get(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Event event) {
        eventDao.save(event);
        return Response.status(Response.Status.CREATED).build();
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") String id) {
        eventDao.delete(id);
    }
}
