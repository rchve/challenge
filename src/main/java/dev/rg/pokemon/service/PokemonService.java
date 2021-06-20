package dev.rg.pokemon.service;

import com.google.common.flogger.FluentLogger;
import dev.rg.pokemon.clients.PokemonRestClient;
import dev.rg.pokemon.clients.TranslationRestClient;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PokemonService {
    private static final FluentLogger logger = FluentLogger.forEnclosingClass();
    private final PokemonRestClient pokemonClient;
    private final TranslationRestClient translationRestClient;

    public PokemonService(@RestClient PokemonRestClient pokemonClient, @RestClient TranslationRestClient translationRestClient) {
        this.pokemonClient = pokemonClient;
        this.translationRestClient = translationRestClient;
    }

    public PokemonData pokemon(String name) {
        logger.atFine().log("Retrieving Pokemon Details for {}", name);
        final var pokemon = pokemonClient.pokemon(name);
        return new PokemonData(pokemon);
    }

    public PokemonData translatedPokemon(String name) {
        final var pokemon = pokemonClient.pokemon(name);
        final var pokemonData = new PokemonData(pokemon);
        final var translation = new DoYodaTranslation().test(pokemon) ? Translation.YODA : Translation.SHAKESPEARE;
        try {
            final var translationResult = translationRestClient.translate(translation.getName(), pokemonData.getEnglishDescription());
            pokemonData.setTranslation(translationResult);
        } catch (Exception e) {
            logger.atWarning().withCause(e).log("unable to get translation using default english message");
        }
        return pokemonData;
    }
}
