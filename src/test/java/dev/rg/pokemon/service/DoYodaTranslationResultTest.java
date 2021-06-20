package dev.rg.pokemon.service;

import dev.rg.pokemon.clients.models.PokemonSpecies;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoYodaTranslationResultTest {

    private DoYodaTranslation predicate;

    @BeforeEach
    void setup() {
        predicate = new DoYodaTranslation();
    }

    @Test
    void shouldReturnTrueForLegendary() {
        final var result = predicate.test(PokemonSpecies.builder().isLegendary(true).build());
        assertTrue(result);
    }

    @Test
    void shouldReturnTrueForCaveHabitat() {
        final var result = predicate.test(PokemonSpecies.builder().isLegendary(false).habitat(PokemonSpecies.Habitat.builder()
                .name("cave").build()).build());
        assertTrue(result);
    }

    @Test
    void shouldReturnForNonLegendaryAndNonCaveHabitat() {
        final var result = predicate.test(PokemonSpecies.builder().isLegendary(false).habitat(PokemonSpecies.Habitat.builder()
                .name("urban").build()).build());
        assertFalse(result);
    }

}