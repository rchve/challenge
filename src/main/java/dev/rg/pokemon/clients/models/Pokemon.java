package dev.rg.pokemon.clients.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(builder = Pokemon.PokemonBuilder.class)
public class Pokemon {
    private final int id;
    private final String name;

    @JsonPOJOBuilder(withPrefix = "")
    public static class PokemonBuilder {}

    @Builder
    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonDeserialize(builder = Pokemon.PokemonSpecies.PokemonSpeciesBuilder.class)
    public static class PokemonSpecies {
        private final int id;
        private final String name;

        @JsonPOJOBuilder(withPrefix = "")
        public static class PokemonSpeciesBuilder {}
    }
}
