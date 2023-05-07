package com.andersenlab.cities.dto;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import lombok.Builder;

/**
 *CreateCityDto
 *
 *@author Aliaksei Tumilovich
 *15.03.2023
 */
@Builder
public record CreateCityRequest (
   @Length(min = 1, max = 200, message = "name_is_required")
   String name,
   @URL
   String url
) {}
