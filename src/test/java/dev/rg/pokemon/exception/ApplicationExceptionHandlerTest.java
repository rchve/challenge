package dev.rg.pokemon.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationExceptionHandlerTest {

    @Test
    void shouldReturnStatusFromException() {
        final var result = new ApplicationExceptionHandler().toResponse(new ApplicationException(Response.Status.INTERNAL_SERVER_ERROR, "Error"));

        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), result.getStatus());
    }

}