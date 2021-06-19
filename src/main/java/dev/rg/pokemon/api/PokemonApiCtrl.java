package dev.rg.pokemon.api;

import dev.rg.pokemon.clients.PokemonRestClient;
import dev.rg.pokemon.clients.models.Pokemon;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/pokemon")
@Produces(APPLICATION_JSON)
public class PokemonApiCtrl {
    private final PokemonRestClient pokemonClient;

    public PokemonApiCtrl(@RestClient PokemonRestClient pokemonClient) {
        this.pokemonClient = pokemonClient;
    }

    @GET
    @Path("/{name}")
    public PokemonResponse info(@PathParam("name") String name) {
        final Pokemon pokemon = pokemonClient.pokemon(name);
        return buildResponse(pokemon);
    }

    private PokemonResponse buildResponse(final Pokemon pokemon) {
        return PokemonResponse.builder()
                .name(pokemon.getName()).build();
    }
}
