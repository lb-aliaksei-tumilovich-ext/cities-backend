package com.andersenlab.cities.service;

import com.andersenlab.cities.dto.CityDto;
import com.andersenlab.cities.dto.CreateCityRequest;
import com.andersenlab.cities.dto.PageResponse;
import com.andersenlab.cities.dto.UpdateCityRequest;

import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 *CityService
 *
 *@author Aliaksei Tumilovich
 *01.03.2023
 */
public interface CityService {
    PageResponse<CityDto> getCities(Pageable pageable, String name);

    CityDto updateCity(Long id, UpdateCityRequest city);

    CityDto getCity(Long id);

    CityDto createCity(CreateCityRequest createCityRequest);
}
