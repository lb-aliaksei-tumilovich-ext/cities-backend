package com.andersenlab.cities.exception.exceptions;

import com.andersenlab.cities.exception.enums.BadRequestErrorEnum;

import lombok.Getter;

/**
 *BadRequestException
 *
 *@author Aliaksei Tumilovich
 *01.03.2023
 */
@Getter
public class BadRequestException extends RuntimeException {
    private final String errorCode;
    private final String message;

    public BadRequestException(BadRequestErrorEnum badRequestErrorEnum) {
        super(badRequestErrorEnum.getMessage());
        this.errorCode = badRequestErrorEnum.getErrorCode();
        this.message = badRequestErrorEnum.getMessage();
    }
}
