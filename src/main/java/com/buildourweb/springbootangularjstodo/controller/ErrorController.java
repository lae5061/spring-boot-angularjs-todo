package com.buildourweb.springbootangularjstodo.controller;


import com.buildourweb.springbootangularjstodo.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@ControllerAdvice
@RestController
public class ErrorController extends ResponseEntityExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(ErrorController.class);

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        final BindingResult bindingResult = ex.getBindingResult();
        logger.error("handleMethodArgumentNotValid");
        logger.error(bindingResult.toString());
        final List<ObjectError> allErrors = bindingResult.getAllErrors();
        final Message message = new Message(true, "", allErrors);
        return new ResponseEntity(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
        logger.error("handleConstraintViolationException");
        logger.error(ex.toString());
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();

        final List<ObjectError> allErrors = new ArrayList<>();
        for (ConstraintViolation<?> constraintViolation : constraintViolations) {
            ObjectError objectError = new ObjectError(
                    constraintViolation.getPropertyPath().toString()
                    , constraintViolation.getMessage());
            allErrors.add(objectError);
        }
        final Message message = new Message(true, "", allErrors);
        return new ResponseEntity(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {
        logger.error("handleAllException");
        logger.error(ex.toString());

        final List<ObjectError> allErrors = new ArrayList<>();
        ObjectError objectError = new ObjectError("error", ex.getMessage());
        allErrors.add(objectError);
        final Message message = new Message(true, "", allErrors);
        return new ResponseEntity(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
