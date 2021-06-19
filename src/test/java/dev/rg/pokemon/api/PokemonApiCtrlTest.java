package dev.rg.pokemon.api;

import com.google.common.collect.Lists;
import dev.rg.pokemon.clients.PokemonRestClient;
import dev.rg.pokemon.clients.models.PokemonSpecies;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;

@QuarkusTest
class PokemonApiCtrlTest {

    @InjectMock
    @RestClient
    PokemonRestClient client;

    @Test
    void testPokemonDitto() {
        Mockito.when(client.pokemon("ditto")).thenReturn(PokemonSpecies.builder().name("ditto")
                .isLegendary(false).flavorTextEntries(Lists.newArrayList()).build());
        given().when().get("/pokemon/ditto")
                .then().statusCode(200)
                .body("name", is("ditto"),
                        "species", is("ditto"),
                        "description", nullValue(),
                        "isLegendary", is(false));
    }

}