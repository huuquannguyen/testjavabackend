package com.example.testjavabackend.handler;

import com.example.testjavabackend.dto.error.ErrorDetails;
import com.example.testjavabackend.dto.error.ValidationError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ControllerAdvice
public class RestExceptionHandler{
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handlerValidationError(MethodArgumentNotValidException manve, HttpServletRequest request){
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setTimeStamp(new Date().getTime());
        errorDetails.setStatus(HttpStatus.BAD_REQUEST.value());
        String requestPath = (String) request.getAttribute("javax.servlet.error.request_uri");
        if(requestPath == null){
            requestPath = request.getRequestURI();
        }
        errorDetails.setPath(requestPath);
        errorDetails.setTitle("Validation Error");
        errorDetails.setDetail("Input validation failed");
        List<FieldError> fieldErrors = manve.getBindingResult().getFieldErrors();
        for (FieldError fe: fieldErrors) {
            List<ValidationError> validationErrorList = errorDetails.getErrors().get(fe.getField());
            if(validationErrorList == null){
                validationErrorList = new ArrayList<>();
                errorDetails.getErrors().put(fe.getField(), validationErrorList);
            }
            ValidationError validationError = new ValidationError();
            validationError.setCode(fe.getCode());
            validationError.setMessage(fe.getDefaultMessage());
            validationErrorList.add(validationError);
        }
        return new ResponseEntity<>(errorDetails, null, HttpStatus.BAD_REQUEST);
    }
}
