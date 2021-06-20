package dev.rg.pokemon.service;

import dev.rg.pokemon.clients.models.PokemonSpecies;
import dev.rg.pokemon.clients.models.TranslationResult;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

@Data
public class PokemonData {
    private PokemonSpecies pokemon;
    private TranslationResult translation;

    public PokemonData(PokemonSpecies pokemon) {
        this.pokemon = pokemon;
    }

    public PokemonData(PokemonSpecies pokemon, TranslationResult translation) {
        this.translation = translation;
        this.pokemon = pokemon;
    }

    public String getEnglishDescription() {
        return Optional.ofNullable(pokemon.getFlavorTextEntries())
                .flatMap(e -> e.stream()
                        .filter(p -> StringUtils.equals("en", p.getLanguage().getName()))
                        .findFirst()
                        .map(PokemonSpecies.FlavorTextEntries::getFlavorText))
                .orElse(null);
    }

    public String getHabitat() {
        return Optional.ofNullable(pokemon.getHabitat())
                .map(PokemonSpecies.Habitat::getName)
                .orElse(null);
    }

    public String getTranslatedDescription() {
        return Optional.ofNullable(translation)
                .map(r -> Optional.ofNullable(r.getContents())
                        .map(TranslationResult.Contents::getTranslated)
                        .orElseGet(this::getEnglishDescription))
                .orElseGet(this::getEnglishDescription);
    }
}
