package com.andersenlab.cities.dto;

import lombok.Builder;

/**
 *ClinicRespDto
 *
 *@author Aliaksei Tumilovich
 *01.03.2023
 */

@Builder
public record CityDto(Long id, String name, String url) {
}
