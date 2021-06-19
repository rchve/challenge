package dev.rg.pokemon.api;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
class PokemonResponse {
    private final String name;
    private final String species;
    private final Boolean isLegendary;
    private final String description;
}
