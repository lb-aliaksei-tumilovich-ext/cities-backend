package com.andersenlab.cities.exception.enums;

import lombok.Getter;

/**
 *VaildationErrorEnum
 *
 *@author Aliaksei Tumilovich
 *02.03.2023
 */
@Getter
public enum ValidationErrorEnum {
    CITY_NAME_REQUIRED_VALIDATION_ERROR("CITIES-VL-0001", "name_is_required"),
    CITY_URL_REQUIRED_VALIDATION_ERROR("CITIES-VL-0002", "url_is_required"),
    PAGE_MUST_BE_POSITIVE("CITIES-VL-0003", "page_must_be_positive"),
    SIZE_MUST_BE_POSITIVE("CITIES-VL-0004", "size_must_be_positive");

    private final String errorCode;
    private final String message;

    ValidationErrorEnum(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }
}
