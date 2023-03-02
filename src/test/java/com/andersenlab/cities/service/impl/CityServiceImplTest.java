package com.andersenlab.cities.service.impl;

import com.andersenlab.cities.AbstractTest;
import com.andersenlab.cities.dto.CityDto;
import com.andersenlab.cities.dto.PageResponse;
import com.andersenlab.cities.exception.enums.BadRequestErrorEnum;
import com.andersenlab.cities.exception.exceptions.BadRequestException;
import com.andersenlab.cities.repository.CityRepository;
import com.andersenlab.cities.utils.CityMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 *CityServiceImplTest
 *
 *@author Aliaksei Tumilovich
 *02.03.2023
 */
@ExtendWith(MockitoExtension.class)
class CityServiceImplTest extends AbstractTest {
    @Mock
    private CityRepository cityRepository;
    private CityMapper mapper;
    private CityServiceImpl cityService;

    @BeforeEach
    public void init() {
        mapper = new CityMapper();
        cityService = new CityServiceImpl(cityRepository, mapper);
    }

    @Test
    public void getCities_success() {
        when(cityRepository.findAll((Pageable) any())).thenReturn(new PageImpl<>(CITIES));

        PageResponse<CityDto> cities = cityService.getCities(PageRequest.of(0,1), null);

        assertEquals(cities.data().size(), 2);
        assertEquals(cities.data().get(0).url(), CITY_1.getUrl());
        assertEquals(cities.data().get(0).name(), CITY_1.getName());
        assertEquals(cities.data().get(1).url(), CITY_2.getUrl());
        assertEquals(cities.data().get(1).name(), CITY_2.getName());
    }

    @Test
    public void getCities_byName_success() {
        when(cityRepository.findAllByNameContainsIgnoreCase(any(), any())).thenReturn(new PageImpl<>(CITIES));

        PageResponse<CityDto> cities = cityService.getCities(PageRequest.of(0,1), "city");

        assertEquals(cities.data().size(), 2);
        assertEquals(cities.data().get(0).url(), CITY_1.getUrl());
        assertEquals(cities.data().get(0).name(), CITY_1.getName());
        assertEquals(cities.data().get(1).url(), CITY_2.getUrl());
        assertEquals(cities.data().get(1).name(), CITY_2.getName());
    }

    @Test
    public void updateCity_success() {
        when(cityRepository.findById(any())).thenReturn(Optional.of(CITY_1));
        when(cityRepository.save(any())).thenReturn(CITY_1);

        assertDoesNotThrow(() -> cityService.updateCity(CITY_1.getId(), UPDATE_CITY));
    }

    @Test
    public void updateCity_trows() {
        when(cityRepository.findById(any())).thenReturn(Optional.empty());

        BadRequestException actual = assertThrows(BadRequestException.class, () -> cityService.updateCity(CITY_1.getId(), UPDATE_CITY));

        assertEquals(BadRequestErrorEnum.CITY_NOT_EXISTS.getMessage(), actual.getMessage());
        assertEquals(BadRequestErrorEnum.CITY_NOT_EXISTS.getErrorCode(), actual.getErrorCode());
    }

    @Test
    public void getCity_success() {
        when(cityRepository.findById(any())).thenReturn(Optional.of(CITY_2));

        CityDto city = cityService.getCity(CITY_2.getId());

        assertEquals(city.url(), CITY_2.getUrl());
        assertEquals(city.name(), CITY_2.getName());
    }

    @Test
    public void getCity_throws() {
        when(cityRepository.findById(any())).thenReturn(Optional.empty());

        BadRequestException actual = assertThrows(BadRequestException.class, () -> cityService.getCity(CITY_2.getId()));

        assertEquals(BadRequestErrorEnum.CITY_NOT_EXISTS.getMessage(), actual.getMessage());
        assertEquals(BadRequestErrorEnum.CITY_NOT_EXISTS.getErrorCode(), actual.getErrorCode());
    }
}
