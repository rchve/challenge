package dev.rg.pokemon.clients;

import dev.rg.pokemon.clients.models.TranslationResult;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/translate")
@ApplicationScoped
@RegisterRestClient(configKey = "translation-api")
public interface TranslationRestClient {

    @GET
    @Path("/{name}.json")
    @Produces(MediaType.APPLICATION_JSON)
    TranslationResult translate(@PathParam String name, @QueryParam("text") String text);
}
