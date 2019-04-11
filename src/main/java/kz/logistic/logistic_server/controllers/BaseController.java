package kz.logistic.logistic_server.controllers;

import kz.logistic.logistic_server.exceptions.ServiceException;
import kz.logistic.logistic_server.shared.utils.responses.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author Assylkhan
 * on 10.04.2019
 * @project logistic_server
 */
public class BaseController {

    protected ResponseEntity<?> buildResponse(Object data, HttpStatus httpStatus){
        return new ResponseEntity<>(data , httpStatus);
    }

    protected ErrorResponse buildErrorResponse(ServiceException serviceException){
        return ErrorResponse.builder()
                .code(serviceException.getErrorCode())
                .message(serviceException.getMessage())
                .build();
    }

}
