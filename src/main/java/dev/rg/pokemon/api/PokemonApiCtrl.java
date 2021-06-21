package dev.rg.pokemon.api;

import dev.rg.pokemon.exception.ApplicationException;
import dev.rg.pokemon.service.PokemonService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/pokemon")
@Produces(APPLICATION_JSON)
public class PokemonApiCtrl {
    private final PokemonService pokemonService;

    public PokemonApiCtrl(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GET
    @Path("/{name}")
    public PokemonResponse pokemon(@PathParam("name") String name) throws ApplicationException {
        try {
            final var pokemon = pokemonService.pokemon(name);
            return PokemonResponse.builder()
                    .name(pokemon.getPokemon().getName())
                    .isLegendary(pokemon.getPokemon().isLegendary())
                    .description(pokemon.getEnglishDescription())
                    .habitat(pokemon.getHabitat())
                    .build();
        } catch (Exception e) {
            // Currently populating the Exception message but here is the place for error transformation
            throw new ApplicationException(Response.Status.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GET
    @Path("/translated/{name}")
    public PokemonResponse translatedPokemon(@PathParam("name") String name) {
        try {
            final var pokemon = pokemonService.translatedPokemon(name);
            return PokemonResponse.builder()
                    .name(pokemon.getPokemon().getName())
                    .isLegendary(pokemon.getPokemon().isLegendary())
                    .description(pokemon.getTranslatedDescription())
                    .habitat(pokemon.getHabitat())
                    .build();
        } catch (Exception e) {
            throw new ApplicationException(Response.Status.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

}
