package dev.rg.pokemon.service;

import dev.rg.pokemon.clients.models.PokemonSpecies;
import lombok.Builder;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

@Builder
@Getter
public class PokemonData {
    private final PokemonSpecies pokemon;

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
}
