package com.broadfactor.handler;

import com.broadfactor.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Response<ErrorResponse>> validationException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        Response<ErrorResponse> response = new Response<>();
        ex.getBindingResult().getAllErrors().forEach(error -> response.setErrors(((FieldError) error).getField(), error.getDefaultMessage()));

        Integer code = HttpStatus.BAD_REQUEST.value();
        String path = request.getRequestURI();
        String message = "Field Validation failed";
        String detail = "Verifique os campos";

        response.setData(new ErrorResponse(code, path, message, detail));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

//    @ExceptionHandler(value = SQLIntegrityConstraintViolationException.class)
//    public ResponseEntity<Response<ErrorResponse>> sqlViolationException() {
//
//    }

}
