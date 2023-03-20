package com.fsmile.config.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponse;

import java.util.List;

/**
 * @author raphael
 * @project fsmile
 * @package com.fsmile.config.exceptions
 * @date 3/7/23 : 10:22 PM
 */


public record ErrorMessageImpl(HttpStatus status, String message, List<String> errors) implements ErrorResponse {

    @Override
    public HttpStatusCode getStatusCode() {
        return status;
    }

    @Override
    public ProblemDetail getBody() {
        return ProblemDetail.forStatusAndDetail(status, message);
    }
}
