package dev.rg.pokemon.service;

import dev.rg.pokemon.clients.PokemonRestClient;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PokemonService {
    private final PokemonRestClient pokemonClient;

    public PokemonService(@RestClient PokemonRestClient pokemonClient) {
        this.pokemonClient = pokemonClient;
    }

    public PokemonData pokemon(String name) {
        final var pokemon = pokemonClient.pokemon(name);
        return PokemonData.builder().pokemon(pokemon).build();
    }
}
