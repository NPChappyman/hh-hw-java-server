package example.resource;

import example.model.Vacancy;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.*;

@Path("/vacancies")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VacancyResource {

    private static final Map<Integer, Vacancy> DB = new HashMap<>();

    static {
        DB.put(1, new Vacancy(1, "Java Developer", "Google"));
        DB.put(2, new Vacancy(2, ".NET Developer", "Microsoft"));
    }


    @GET
    public Collection<Vacancy> getAll() {
        return DB.values();
    }


    @GET
    @Path("/{id}")
    public Vacancy getById(@PathParam("id") int id) {
        return DB.get(id);
    }


    @POST
    public String createVacancy(Vacancy vacancy) {
        DB.put(vacancy.id, vacancy);
        return "created";
    }
}