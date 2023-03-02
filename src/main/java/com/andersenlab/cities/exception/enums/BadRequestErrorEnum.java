package com.andersenlab.cities.exception.enums;

import lombok.Getter;

/**
 *BadRequestEnum
 *
 *@author Aliaksei Tumilovich
 *01.03.2023
 */
@Getter
public enum BadRequestErrorEnum {

    BAD_REQUEST("CITIES-BR-0001", "bad_request_exception"),
    CITY_NOT_EXISTS("CITIES-BR-0002", "city_not_exists"),;

    private final String errorCode;
    private final String message;

    BadRequestErrorEnum(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }
}

