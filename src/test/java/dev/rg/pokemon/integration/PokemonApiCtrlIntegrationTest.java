package dev.rg.pokemon.integration;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.Is.is;

@QuarkusTest
@QuarkusTestResource(WiremockPokemon.class)
class PokemonApiCtrlIntegrationTest {

    @Test
    void testPokemonDitto() {
        given().when().get("/pokemon/ditto")
                .then().statusCode(200)
                .body("name", is("ditto"),
                        "habitat", is("urban"),
                        "description", containsString("It can freely recombine its own cellular structure to"),
                        "isLegendary", is(false));
    }
}
