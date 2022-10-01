package com.broadfactor.handler;

import com.broadfactor.handler.exceptions.EntityNotFoundException;
import com.broadfactor.handler.exceptions.ParseFormatterErrorException;
import com.broadfactor.response.Response;
import org.springframework.dao.DataIntegrityViolationException;
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
    public ResponseEntity<Response<ErrorResponse>> methodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        Response<ErrorResponse> response = new Response<>();
        ex.getBindingResult().getAllErrors().forEach(error -> response.setErrors(((FieldError) error).getField(), error.getDefaultMessage()));

        ErrorResponse error = ErrorResponse
                .builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .path(request.getRequestURI())
                .message("Field Validation failed")
                .detail("Verifique os campos")
                .build();

        response.setData(error);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity<Response<ErrorResponse>> dataIntegrityViolationException(HttpServletRequest request) {
        int status = HttpStatus.NOT_FOUND.value();
        String path = request.getRequestURI();
        String message = "Dados já cadastrado em nossa base de dados!";
        String detail = "Verifique os campos e tente novamente!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(jsonError(status, path, message, detail));
    }

    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<Response<ErrorResponse>> entityNotFoundException(EntityNotFoundException ex, HttpServletRequest request) {
        int status = HttpStatus.NOT_FOUND.value();
        String path = request.getRequestURI();
        String message = ex.getMessage();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(jsonError(status, path, message, null));
    }

    @ExceptionHandler(value = ParseFormatterErrorException.class)
    public ResponseEntity<Response<ErrorResponse>> parseFormatterErrorException(ParseFormatterErrorException ex, HttpServletRequest request) {
        int status = HttpStatus.BAD_REQUEST.value();
        String path = request.getRequestURI();
        String message = ex.getMessage();
        String detail = "Verifique se o cnpj informado esta correto!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(jsonError(status, path, message, detail));
    }


    private Response<ErrorResponse> jsonError(int code, String path, String message, String detail) {
        Response<ErrorResponse> response = new Response<>();
        ErrorResponse error = ErrorResponse
                .builder()
                .status(code)
                .path(path)
                .message(message)
                .detail(detail)
                .build();
        response.setData(error);
        return response;
    }
}
