package dev.rg.pokemon.service;

import dev.rg.pokemon.clients.models.PokemonSpecies;
import org.apache.commons.lang3.StringUtils;

import java.util.function.Predicate;

public class DoYodaTranslation implements Predicate<PokemonSpecies> {
    @Override
    public boolean test(PokemonSpecies pokemon) {
        return pokemon.isLegendary() || (pokemon.getHabitat() != null && StringUtils.equals(pokemon.getHabitat().getName(), "cave"));
    }
}
