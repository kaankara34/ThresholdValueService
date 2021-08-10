package com.aegon.getthresholdvalue.exception;

import com.aegon.cnf.support.rest.ResponseBuilder;
import com.aegon.cnf.support.rest.dto.ServiceResponse;
import com.aegon.getthresholdvalue.model.GetThresholdValueResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public final ResponseEntity<ServiceResponse<GetThresholdValueResponse>> handleBadRequestExceptions(Exception ex, WebRequest request) {
        return ResponseBuilder.warning(null, ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ServiceResponse<GetThresholdValueResponse>> handleAllExceptions(Exception ex, WebRequest request) {
        return ResponseBuilder.warning(null, ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
