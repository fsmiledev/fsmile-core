package com.fsmile.config.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
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

@Data
@AllArgsConstructor
public class ErrorMessageImpl implements ErrorResponse {

    private final HttpStatus status;
    private final String message;
    private final List<String> errors;
    @Override
    public HttpStatusCode getStatusCode() {
        return status;
    }

    @Override
    public ProblemDetail getBody() {
        return  ProblemDetail.forStatusAndDetail(status, message);
    }
}
