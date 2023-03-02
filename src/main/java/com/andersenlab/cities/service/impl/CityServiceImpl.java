package com.andersenlab.cities.service.impl;

import com.andersenlab.cities.dto.CityDto;
import com.andersenlab.cities.dto.PageResponse;
import com.andersenlab.cities.dto.UpdateCityRequest;
import com.andersenlab.cities.exception.enums.BadRequestErrorEnum;
import com.andersenlab.cities.exception.exceptions.BadRequestException;
import com.andersenlab.cities.model.City;
import com.andersenlab.cities.repository.CityRepository;
import com.andersenlab.cities.service.CityService;
import com.andersenlab.cities.utils.CityMapper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

import liquibase.util.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 *CityServiceImpl
 *
 *@author Aliaksei Tumilovich
 *01.03.2023
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;
    private final CityMapper mapper;

    @Override
    public PageResponse<CityDto> getCities(Pageable pageRequest, String name) {
        log.info("get cities by pageable {} and name {}", pageRequest, name);
        Page<City> cities;
        if (StringUtil.isNotEmpty(name)) {
            cities = cityRepository.findAllByNameContainsIgnoreCase(pageRequest, name);
        } else {
            cities = cityRepository.findAll(pageRequest);
        }
        return mapper.mapToPageResponse(cities);
    }

    @Override
    public CityDto updateCity(Long id, UpdateCityRequest city) {
        log.info("update city {} by id {}", city, id);
        Optional<City> optionalCity = cityRepository.findById(id);
        if (optionalCity.isEmpty()) {
            throw new BadRequestException(BadRequestErrorEnum.CITY_NOT_EXISTS);
        } else {
            City cityToUpdate = optionalCity.get();
            cityToUpdate.setName(city.name());
            cityToUpdate.setUrl(city.url());
            City savedCity = cityRepository.save(cityToUpdate);
            return mapper.mapToCityDto(savedCity);
        }
    }

    @Override public CityDto getCity(Long id) {
        log.info("get city by id {}", id);
        Optional<City> optionalCity = cityRepository.findById(id);
        City city = optionalCity.orElseThrow(() -> new BadRequestException(BadRequestErrorEnum.CITY_NOT_EXISTS));
        return mapper.mapToCityDto(city);
    }
}
