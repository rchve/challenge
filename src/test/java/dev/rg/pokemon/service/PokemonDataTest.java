package dev.rg.pokemon.service;

import dev.rg.pokemon.clients.models.PokemonSpecies;
import org.junit.jupiter.api.Test;

import static com.google.common.collect.Lists.newArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PokemonDataTest {

    @Test
    void shouldReturnNullIfFlavorEntriesNotPresent() {
        var data = PokemonData.builder()
                .pokemon(PokemonSpecies.builder().build()).build();
        assertNull(data.getEnglishDescription());
    }

    @Test
    void shouldReturnNullIfEnglishFlavorNotPresent() {
        var data = PokemonData.builder()
                .pokemon(PokemonSpecies.builder().flavorTextEntries(newArrayList()).build()).build();
        assertNull(data.getEnglishDescription());
    }

    @Test
    void shouldReturnEnglishDescription() {
        var data = PokemonData.builder()
                .pokemon(PokemonSpecies.builder().flavorTextEntries(newArrayList(
                        PokemonSpecies.FlavorTextEntries.builder().flavorText("Text")
                                .language(PokemonSpecies.FlavorTextEntries.Language.builder().name("en").build()).build()
                )).build()).build();
        assertEquals("Text", data.getEnglishDescription());
    }

    @Test
    void shouldReturnHabitatName() {
        var data = PokemonData.builder()
                .pokemon(PokemonSpecies.builder().habitat(PokemonSpecies.Habitat.builder().name("habitat").build()).build())
                .build();
        assertEquals("habitat", data.getHabitat());
    }

    @Test
    void shouldReturnNullHabitat() {
        var data = PokemonData.builder()
                .pokemon(PokemonSpecies.builder().build())
                .build();
        assertNull(data.getHabitat());
    }

}