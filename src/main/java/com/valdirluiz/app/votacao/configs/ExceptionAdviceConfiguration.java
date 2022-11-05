package com.valdirluiz.app.votacao.configs;

import com.valdirluiz.app.votacao.exceptions.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class ExceptionAdviceConfiguration extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> badRequest(BadRequestException e, WebRequest request) {
        this.logger.error("Error " + e + " path " + ((ServletWebRequest) request).getRequest().getRequestURI());
        var apiError = new GeneralResponse(e.getMessage());
        return new ResponseEntity<>(apiError, BAD_REQUEST);
    }

}
