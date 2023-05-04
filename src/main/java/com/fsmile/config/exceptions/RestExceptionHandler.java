package com.fsmile.config.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;

/**
 * @author raphael
 * @project fsmile
 * @package com.fsmile.config.web
 * @date 3/7/23 : 10:18 PM
 */
@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {


/*    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        ErrorResponse errorResponse = new ErrorMessageImpl(HttpStatus.BAD_REQUEST, "Validation failed", errors);
        return ResponseEntity.badRequest().body(errorResponse);
    }*/


    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGenericException(Exception ex) {
        ErrorResponse errorResponse = new ErrorMessageImpl(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), null);
        HashMap<String, Object> resp = new HashMap<String, Object>();
        resp.put("code", errorResponse.getBody().getTitle());
        resp.put("message", errorResponse.getBody().getDetail());
        ex.printStackTrace();
        return ResponseEntity.status(errorResponse.getStatusCode()).body(resp);
    }
}
