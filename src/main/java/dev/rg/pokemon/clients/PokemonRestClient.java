package dev.rg.pokemon.clients;

import dev.rg.pokemon.clients.models.PokemonSpecies;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api/v2")
@ApplicationScoped
@RegisterRestClient(configKey = "pokemon-api")
public interface PokemonRestClient {

    @GET
    @Path("/pokemon-species/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    PokemonSpecies pokemon(@PathParam String name);
}
