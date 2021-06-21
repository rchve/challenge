package dev.rg.pokemon.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.ws.rs.core.Response;

@Getter
@AllArgsConstructor
public class ApplicationException extends RuntimeException {
    private final Response.Status status;
    private final String message;
}
