package dev.rg.pokemon.clients.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(builder = PokemonSpecies.PokemonSpeciesBuilder.class)
public class PokemonSpecies {
    private final int id;
    private final String name;
    @JsonProperty("is_legendary")
    private final boolean isLegendary;
    @JsonProperty("flavor_text_entries")
    private final List<FlavorTextEntries> flavorTextEntries;

    @JsonPOJOBuilder(withPrefix = "")
    public static class PokemonSpeciesBuilder {}

    @Builder
    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonDeserialize(builder = FlavorTextEntries.FlavorTextEntriesBuilder.class)
    public static class FlavorTextEntries {
        @JsonProperty("flavor_text")
        private final String flavorText;
        private final Language language;

        @JsonPOJOBuilder(withPrefix = "")
        public static class FlavorTextEntriesBuilder {}

        @Builder
        @Getter
        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonDeserialize(builder = Language.LanguageBuilder.class)
        public static class Language {
            private final String name;

            @JsonPOJOBuilder(withPrefix = "")
            public static class LanguageBuilder {}
        }
    }
}
