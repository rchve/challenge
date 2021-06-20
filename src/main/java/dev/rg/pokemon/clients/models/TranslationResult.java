package dev.rg.pokemon.clients.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(builder = TranslationResult.TranslationResultBuilder.class)
public class TranslationResult {
    private final Contents contents;

    @JsonPOJOBuilder(withPrefix = "")
    public static class TranslationResultBuilder{}

    @Builder
    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonDeserialize(builder = Contents.ContentsBuilder.class)
    public static class Contents {
        private final String translated;
        private final String text;
        private final String translation;

        @JsonPOJOBuilder(withPrefix = "")
        public static class ContentsBuilder {}
    }
}
