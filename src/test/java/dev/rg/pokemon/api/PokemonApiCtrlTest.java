package dev.rg.pokemon.api;

import dev.rg.pokemon.clients.PokemonRestClient;
import dev.rg.pokemon.clients.models.Pokemon;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;

@QuarkusTest
class PokemonApiCtrlTest {

    @InjectMock
    @RestClient
    PokemonRestClient client;

    @Test
    void testPokemonDitto() {
        Mockito.when(client.pokemon("ditto")).thenReturn(Pokemon.builder().name("ditto").build());
        given().when().get("/pokemon/ditto")
                .then().statusCode(200)
                .body("name", is("ditto"));
    }

}