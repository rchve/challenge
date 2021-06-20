package dev.rg.pokemon.service;

import dev.rg.pokemon.clients.models.PokemonSpecies;
import dev.rg.pokemon.clients.models.TranslationResult;
import org.junit.jupiter.api.Test;

import static com.google.common.collect.Lists.newArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PokemonDataTest {

    @Test
    void shouldReturnNullIfFlavorEntriesNotPresent() {
        var data = new PokemonData(PokemonSpecies.builder().build());
        assertNull(data.getEnglishDescription());
    }

    @Test
    void shouldReturnNullIfEnglishFlavorNotPresent() {
        var data = new PokemonData(PokemonSpecies.builder().flavorTextEntries(newArrayList()).build());
        assertNull(data.getEnglishDescription());
    }

    @Test
    void shouldReturnEnglishDescription() {
        var data = new PokemonData(PokemonSpecies.builder().flavorTextEntries(newArrayList(
                PokemonSpecies.FlavorTextEntries.builder().flavorText("Text")
                        .language(PokemonSpecies.FlavorTextEntries.Language.builder().name("en").build()).build()
        )).build());
        assertEquals("Text", data.getEnglishDescription());
    }

    @Test
    void shouldReturnHabitatName() {
        var data = new PokemonData(PokemonSpecies.builder().habitat(PokemonSpecies.Habitat.builder().name("habitat").build()).build());
        assertEquals("habitat", data.getHabitat());
    }

    @Test
    void shouldReturnNullHabitat() {
        var data = new PokemonData(PokemonSpecies.builder().build());
        assertNull(data.getHabitat());
    }

    @Test
    void shouldReturnEnglishDescriptionWhenNoTranslation() {
        var data = new PokemonData(PokemonSpecies.builder().flavorTextEntries(newArrayList(
                PokemonSpecies.FlavorTextEntries.builder().flavorText("Text")
                        .language(PokemonSpecies.FlavorTextEntries.Language.builder().name("en").build()).build()
        )).build());
        assertEquals("Text", data.getTranslatedDescription());
    }

    @Test
    void shouldReturnDescriptionFromTranslationData() {
        var data = new PokemonData(PokemonSpecies.builder().build(), TranslationResult.builder().contents(TranslationResult.Contents.builder()
                .translated("translated").build()).build());
        assertEquals("translated", data.getTranslatedDescription());
    }

}