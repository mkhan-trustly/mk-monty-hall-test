package se.comhem.test.montyhall.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Objects;

@ControllerAdvice
@Log4j2
@SuppressWarnings("unused")
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {HttpClientErrorException.class})
    protected ResponseEntity<GameErrorResponse> handleClientErrorException(
            HttpClientErrorException ex, WebRequest request) {
        GameErrorResponse errorResponse = buildErrorResponse(ex, request)
                .status(ex.getStatusCode().value())
                .error(ex.getStatusCode().getReasonPhrase())
                .build();
        return new ResponseEntity<>(errorResponse, ex.getStatusCode());
    }

    @ExceptionHandler(value = {RuntimeException.class})
    protected ResponseEntity<GameErrorResponse> handleRestOfExceptions(
            RuntimeException ex, WebRequest request) {
        GameErrorResponse errorResponse = buildErrorResponse(ex, request)
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .build();
        log.error("Encountered exception of type {} when using service {} stack trace\n {}",
                ex.getClass().getName(), errorResponse.getPath(), ex);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private GameErrorResponse.GameErrorResponseBuilder buildErrorResponse(RuntimeException ex, WebRequest request) {
        String message = Objects.nonNull(ex.getMessage()) ? ex.getMessage() : "Failed to fetch the data";
        return GameErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .message(message)
                .path(((ServletWebRequest) request).getRequest().getRequestURI());
    }
}

