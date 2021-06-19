package dev.rg.pokemon.api;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
class PokemonResponse {
    private final String name;
}
