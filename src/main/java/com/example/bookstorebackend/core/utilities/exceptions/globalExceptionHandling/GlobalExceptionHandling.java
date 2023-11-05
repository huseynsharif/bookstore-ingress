package com.example.bookstorebackend.core.utilities.exceptions.globalExceptionHandling;

import com.example.bookstorebackend.core.utilities.exceptions.customExceptions.*;
import com.example.bookstorebackend.core.utilities.exceptions.entities.ExceptionResponseBody;
import com.example.bookstorebackend.core.utilities.results.DataResult;
import com.example.bookstorebackend.core.utilities.results.ErrorDataResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@ControllerAdvice
public class GlobalExceptionHandling {

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<DataResult<ExceptionResponseBody>> handlingNotFound(){

        ExceptionResponseBody exceptionResponseBody = new ExceptionResponseBody(
                404,
                "User not found",
                LocalDateTime.now()
        );

        return new ResponseEntity<>(new ErrorDataResult<>(exceptionResponseBody, "Something went wrong."), HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<DataResult<?>> handlingMethodArgumentValid(MethodArgumentNotValidException exceptions){
        Map<String, String> validationErrors = new HashMap<>();
        for(FieldError fieldError : exceptions.getBindingResult().getFieldErrors()) {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return new ResponseEntity<>(new ErrorDataResult<>(validationErrors, "validation error"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = UsernameAlreadyExistsException.class)
    public ResponseEntity<DataResult<ExceptionResponseBody>> handlingUsernameAlreadyExists(){

        ExceptionResponseBody exceptionResponseBody = new ExceptionResponseBody(
                400,
                "Username Already exists",
                LocalDateTime.now()
        );

        return new ResponseEntity<>(new ErrorDataResult<>(exceptionResponseBody, "Something went wrong."), HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(value = EmailAlreadyExistsException.class)
    public ResponseEntity<DataResult<ExceptionResponseBody>> handlingEmailAlreadyExists(){

        ExceptionResponseBody exceptionResponseBody = new ExceptionResponseBody(
                400,
                "Email Already exists",
                LocalDateTime.now()
        );

        return new ResponseEntity<>(new ErrorDataResult<>(exceptionResponseBody, "Something went wrong."), HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(value = PasswordIsNotSameException.class)
    public ResponseEntity<DataResult<ExceptionResponseBody>> handlingPasswordsIsNotSame(){

        ExceptionResponseBody exceptionResponseBody = new ExceptionResponseBody(
                400,
                "Passwords must be same.",
                LocalDateTime.now()
        );

        return new ResponseEntity<>(new ErrorDataResult<>(exceptionResponseBody, "Something went wrong."), HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(value = TokenExpiredException.class)
    public ResponseEntity<DataResult<ExceptionResponseBody>> handlingTokenExpiredException(){

        ExceptionResponseBody exceptionResponseBody = new ExceptionResponseBody(
                400,
                "Token is expired.",
                LocalDateTime.now()
        );

        return new ResponseEntity<>(new ErrorDataResult<>(exceptionResponseBody, "Something went wrong."), HttpStatus.NOT_ACCEPTABLE);

    }

    @ExceptionHandler(value = IncorrectTokenException.class)
    public ResponseEntity<DataResult<ExceptionResponseBody>> handlingIncorrectToken(){

        ExceptionResponseBody exceptionResponseBody = new ExceptionResponseBody(
                400,
                "Token is not correct.",
                LocalDateTime.now()
        );

        return new ResponseEntity<>(new ErrorDataResult<>(exceptionResponseBody, "Something went wrong."), HttpStatus.NOT_ACCEPTABLE);

    }

    @ExceptionHandler(value = UserIsNotVerifiedException.class)
    public ResponseEntity<DataResult<ExceptionResponseBody>> handlingUserIsNotVerified(){

        ExceptionResponseBody exceptionResponseBody = new ExceptionResponseBody(
                400,
                "User is not verified yet.",
                LocalDateTime.now()
        );

        return new ResponseEntity<>(new ErrorDataResult<>(exceptionResponseBody, "Something went wrong."), HttpStatus.NOT_ACCEPTABLE);

    }

    @ExceptionHandler(value = IllegalDeletionRequestException.class)
    public ResponseEntity<DataResult<ExceptionResponseBody>> handlingIllegalDeletionRequest(){

        ExceptionResponseBody exceptionResponseBody = new ExceptionResponseBody(
                400,
                "It is forbidden to delete other's book.",
                LocalDateTime.now()
        );

        return new ResponseEntity<>(new ErrorDataResult<>(exceptionResponseBody, "Something went wrong."), HttpStatus.NOT_ACCEPTABLE);

    }



}
