package dev.rg.pokemon.exception;

import com.google.common.flogger.FluentLogger;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.Map;

@Provider
public class ApplicationExceptionHandler implements ExceptionMapper<ApplicationException> {
    private static final FluentLogger logger = FluentLogger.forEnclosingClass();

    @Override
    public Response toResponse(ApplicationException exception) {
        logger.atSevere().withCause(exception).log(exception.getMessage());
        return Response.status(exception.getStatus())
                .entity(Map.of("message", exception.getMessage()))
                .build();
    }
}
