package com.fsmile.utils;

import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponse;

import java.net.URI;
import java.util.List;

/**
 * Project trunk
 * Package com.fsmile.utils
 * Author revouna
 * Date 07/03/2023
 */

@Data
public class ErrorResponseUtils implements ErrorResponse {

    private HttpStatus status;
    private URI uri;
    private String message;
    private List<String> errors;


    @Override
    public HttpStatusCode getStatusCode() {
        return status;
    }

    @Override
    public ProblemDetail getBody() {
        return new ProblemDetail(uri);
    }
}
