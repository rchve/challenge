package dev.rg.pokemon.api;

import dev.rg.pokemon.clients.PokemonRestClient;
import dev.rg.pokemon.clients.models.PokemonSpecies;
import org.apache.commons.lang3.StringUtils;
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
    public PokemonResponse pokemon(@PathParam("name") String name) {
        final var pokemon = pokemonClient.pokemon(name);
        return buildResponse(pokemon);
    }

    private PokemonResponse buildResponse(final PokemonSpecies pokemon) {
        final PokemonResponse.PokemonResponseBuilder builder = PokemonResponse.builder()
                .name(pokemon.getName())
                .isLegendary(pokemon.isLegendary());
        if (pokemon.getHabitat() != null) {
            builder.habitat(pokemon.getHabitat().getName());
        }
        pokemon.getFlavorTextEntries().stream().filter(p -> StringUtils.equals("en", p.getLanguage().getName())).findFirst()
                .ifPresent(v -> builder.description(v.getFlavorText()));
        return builder.build();
    }
}
