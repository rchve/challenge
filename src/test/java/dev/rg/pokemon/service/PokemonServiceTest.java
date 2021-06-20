package dev.rg.pokemon.service;

import dev.rg.pokemon.clients.PokemonRestClient;
import dev.rg.pokemon.clients.models.PokemonSpecies;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PokemonServiceTest {
    private PokemonService service;
    private PokemonRestClient restClient;

    @BeforeEach
    void setup() {
        restClient = Mockito.mock(PokemonRestClient.class);
        service = new PokemonService(restClient);
    }

    @Test
    void shouldReturnPokemonData(){
        Mockito.when(restClient.pokemon("ditto")).thenReturn(PokemonSpecies.builder().name("ditto").build());
        final var result = service.pokemon("ditto");

        assertEquals("ditto", result.getPokemon().getName());
    }

}