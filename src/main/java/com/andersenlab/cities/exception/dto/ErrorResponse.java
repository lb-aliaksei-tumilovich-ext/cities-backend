package com.andersenlab.cities.exception.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *ErrorResponse
 *
 *@author Aliaksei Tumilovich
 *02.03.2023
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

    private String errorMessage;
    private String path;
    private String errorCode;
    @Builder.Default
    private Date date = new Date();
}
