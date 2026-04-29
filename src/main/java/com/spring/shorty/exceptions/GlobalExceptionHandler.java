package com.spring.shorty.exceptions;

import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.spring.shorty.dto.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse> handleNotFound(RuntimeException ex) {
        return ResponseEntity.status(404)
                .body(new ApiResponse(ex.getMessage(), 404));
    }
    

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiResponse> handleBadRequest(BadRequestException ex) {
        return ResponseEntity
                .badRequest()
                .body(new ApiResponse(ex.getMessage(),400));
    }
    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ApiResponse> handleConflict(ConflictException ex) {
        return ResponseEntity
                .status(409)
                .body(new ApiResponse(ex.getMessage(), 409));
    }

    @ExceptionHandler(Exception.class)	
    public ResponseEntity<ApiResponse> handleGeneric(Exception ex) {
        return ResponseEntity
                .status(500)
                .body(new ApiResponse("Erro interno", 500));
    }
    
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ApiResponse> handleUnauthorized(UnauthorizedException ex){
        return ResponseEntity
        		.status(401)
        		.body(new ApiResponse("invalid credentials", 401));
    }
}	