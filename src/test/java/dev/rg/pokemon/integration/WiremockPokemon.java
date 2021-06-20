package dev.rg.pokemon.integration;

import com.github.tomakehurst.wiremock.WireMockServer;
import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class WiremockPokemon implements QuarkusTestResourceLifecycleManager {

    private WireMockServer wireMockServer;

    @Override
    public Map<String, String> start() {
        wireMockServer = new WireMockServer();
        wireMockServer.start();
        try {
            stubFor(get(urlEqualTo("/api/v2/pokemon-species/ditto")).willReturn(
                    aResponse().withHeader("Content-Type", "application/json")
                            .withBody(Files.readString(Path.of("", "src/test/resources/pokemon/ditto.json")))
            ));
            stubFor(get(urlPathMatching("/translate/.*\\.json")).willReturn(
                    aResponse().withHeader("Content-Type", "application/json")
                            .withBody(Files.readString(Path.of("", "src/test/resources/translation/translate.json")))
            ));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Map.of("pokemon-api/mp-rest/url", wireMockServer.baseUrl(), "translation-api/mp-rest/url", wireMockServer.baseUrl());
    }

    @Override
    public void stop() {
        if (wireMockServer != null) {
            wireMockServer.stop();
        }
    }
}
