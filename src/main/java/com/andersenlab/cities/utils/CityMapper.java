package com.andersenlab.cities.utils;

import com.andersenlab.cities.dto.CityDto;
import com.andersenlab.cities.dto.CreateCityRequest;
import com.andersenlab.cities.dto.PageResponse;
import com.andersenlab.cities.model.City;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 *ModelMapper
 *
 *@author Aliaksei Tumilovich
 *06.03.2023
 */
@Component
public class CityMapper {
    public PageResponse<CityDto> mapToPageResponse(Page<City> cityPage) {
        return PageResponse.<CityDto>builder()
           .currentPage(cityPage.getNumber())
           .totalElements(cityPage.getTotalElements())
           .totalPages(cityPage.getTotalPages())
           .data(cityPage.getContent()
              .stream()
              .map(this::mapToCityDto)
              .collect(Collectors.toList()))
           .build();
    }

    public CityDto mapToCityDto(City city) {

        return CityDto.builder()
           .id(city.getId())
           .name(city.getName())
           .url(city.getUrl())
           .build();
    }

    public City mapToCity(CreateCityRequest createCityRequest) {

        return City.builder()
           .name(createCityRequest.name())
           .url(createCityRequest.url())
           .build();
    }
}
