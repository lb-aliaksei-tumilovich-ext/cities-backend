package com.andersenlab.cities.exception;

import com.andersenlab.cities.exception.dto.ErrorResponse;
import com.andersenlab.cities.exception.enums.BadRequestErrorEnum;
import com.andersenlab.cities.exception.exceptions.BadRequestException;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.stream.Collectors;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;

/**
 *ExceptionHandler
 *
 *@author Aliaksei Tumilovich
 *02.03.2023
 */
@ControllerAdvice
@Slf4j
public class ExceptionController {

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleAuthorization(final BadRequestException badRequestException,
       final HttpServletRequest request) {
        return ErrorResponse.builder()
           .errorMessage(badRequestException.getMessage())
           .errorCode(badRequestException.getErrorCode())
           .path(request.getRequestURI())
           .build();
    }

    @ExceptionHandler({MethodArgumentNotValidException.class,
       MethodArgumentTypeMismatchException.class,
       ConstraintViolationException.class,
       HttpMessageNotReadableException.class,
       MethodArgumentTypeMismatchException.class,
       ConstraintViolationException.class,
       ServletRequestBindingException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleConstraintViolation(final Exception ex, final HttpServletRequest request) {
        String errorMessage;
        if (ex instanceof ConstraintViolationException constraintViolationException) {
            errorMessage = constraintViolationException.getConstraintViolations()
               .stream()
               .map(ConstraintViolation::getMessage)
               .collect(Collectors.joining(", "));
        } else if (ex instanceof MethodArgumentNotValidException methodArgumentNotValidException) {
            errorMessage = methodArgumentNotValidException.getBindingResult()
               .getFieldErrors()
               .stream()
               .map(DefaultMessageSourceResolvable::getDefaultMessage)
               .collect(Collectors.joining(", "));
        } else {
            errorMessage = BadRequestErrorEnum.BAD_REQUEST.getMessage();
        }

        return ErrorResponse.builder()
           .errorMessage(errorMessage)
           .errorCode(String.valueOf(HttpStatus.BAD_REQUEST.value()))
           .path(request.getRequestURI())
           .build();
    }
}
