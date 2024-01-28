package com.devcommunity.community.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.devcommunity.community.exceptions.post.PostNotFoundException;
import com.devcommunity.community.exceptions.user.UserNotFoundException;
import com.mongodb.DuplicateKeyException;
import com.mongodb.MongoTimeoutException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<JsonErrorMessage> userNotFoundHandler(UserNotFoundException exception) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, "User not found.");
    }

    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<JsonErrorMessage> postNotFoundHandler(PostNotFoundException exception) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, "Post not found.");
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<JsonErrorMessage> handleMethodArgumentTypeMismatch(
            MethodArgumentTypeMismatchException exception) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, "Method argument type mismatch.");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<JsonErrorMessage> handleAll(Exception exception) {
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred.");
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<JsonErrorMessage> nullPointerHandler(NullPointerException exception) {
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred.");
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<JsonErrorMessage> handleDuplicateKey(DuplicateKeyException exception) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, "Duplicate key error");
    }

    @ExceptionHandler(MongoTimeoutException.class)
    public ResponseEntity<JsonErrorMessage> handleMongoTimeout(MongoTimeoutException exception) {
        return buildErrorResponse(HttpStatus.GATEWAY_TIMEOUT, "MongoDB connection timeout.");
    }

    private ResponseEntity<JsonErrorMessage> buildErrorResponse(HttpStatus status, String message) {
        JsonErrorMessage errorMessage = new JsonErrorMessage(status, message);
        return ResponseEntity.status(status).body(errorMessage);
    }
}