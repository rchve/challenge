package dev.rg.pokemon.service;

import dev.rg.pokemon.clients.PokemonRestClient;
import dev.rg.pokemon.clients.TranslationRestClient;
import dev.rg.pokemon.clients.models.PokemonSpecies;
import dev.rg.pokemon.clients.models.TranslationResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class PokemonServiceTest {
    private PokemonService service;
    private PokemonRestClient restClient;
    private TranslationRestClient translationRestClient;

    @BeforeEach
    void setup() {
        restClient = Mockito.mock(PokemonRestClient.class);
        translationRestClient = Mockito.mock(TranslationRestClient.class);
        service = new PokemonService(restClient, translationRestClient);
    }

    @Test
    void shouldReturnPokemonData(){
        Mockito.when(restClient.pokemon("ditto")).thenReturn(PokemonSpecies.builder().name("ditto").build());
        final var result = service.pokemon("ditto");

        assertEquals("ditto", result.getPokemon().getName());
    }

    @Test
    void shouldSetTranslationResult() {
        Mockito.when(restClient.pokemon("ditto")).thenReturn(PokemonSpecies.builder().name("ditto").build());
        Mockito.when(translationRestClient.translate(any(), any())).thenReturn(TranslationResult.builder().build());
        final var result = service.translatedPokemon("ditto");

        assertNotNull(result.getTranslation());
    }

    @Test
    void shouldSkipTranslationResultWhenTranslationApiFails() {
        Mockito.when(restClient.pokemon("ditto")).thenReturn(PokemonSpecies.builder().name("ditto").build());
        Mockito.when(translationRestClient.translate(any(), any())).thenThrow(new RuntimeException());
        final var result = service.translatedPokemon("ditto");

        assertNull(result.getTranslation());
    }

}