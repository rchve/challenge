package dev.rg.pokemon.api;

import com.google.common.collect.Lists;
import dev.rg.pokemon.clients.models.PokemonSpecies;
import dev.rg.pokemon.exception.ApplicationException;
import dev.rg.pokemon.service.PokemonData;
import dev.rg.pokemon.service.PokemonService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class PokemonApiCtrlTest {

    private PokemonApiCtrl subject;
    private PokemonService pokemonService;

    @BeforeEach
    void setup() {
        pokemonService = Mockito.mock(PokemonService.class);
        subject = new PokemonApiCtrl(pokemonService);
    }

    @Test
    void testPokemonDitto() {
        when(pokemonService.pokemon("ditto")).thenReturn(
                new PokemonData(PokemonSpecies.builder().name("ditto")
                        .isLegendary(false).flavorTextEntries(Lists.newArrayList())
                        .habitat(PokemonSpecies.Habitat.builder().name("urban").build()).build()));

        final var result = subject.pokemon("ditto");

        assertEquals("ditto", result.getName());
    }

    @Test
    void testPokemonWithBackendApiError() {
        when(pokemonService.pokemon("ditto")).thenThrow(new RuntimeException());
        Assertions.assertThrows(ApplicationException.class, () -> subject.pokemon("ditto"));
    }

}