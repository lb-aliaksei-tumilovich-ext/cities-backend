package com.andersenlab.cities.dto;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *UpdateCityDto
 *
 *@author Aliaksei Tumilovich
 *02.03.2023
 */
@Builder
public record UpdateCityRequest (
   @Length(min = 1, max = 200, message = "name_is_required")
   String name,
   @URL
   String url

) {}
