package com.andersenlab.cities.utils;

import com.andersenlab.cities.AbstractTest;
import com.andersenlab.cities.dto.CityDto;
import com.andersenlab.cities.dto.PageResponse;
import com.andersenlab.cities.model.City;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *ModelMapperTest
 *
 *@author Aliaksei Tumilovich
 *06.03.2023
 */
@ExtendWith(MockitoExtension.class)
class ModelMapperTest extends AbstractTest {

    public static final int PAGE = 0;
    public static final int SIZE = 5;
    public static final int TOTAL_PAGES = 1;
    public static final int TOTAL_ELEMENTS = 2;
    private final Mapper mapper = new Mapper();
    Page<City> page = new PageImpl<>(CITIES, PageRequest.of(PAGE, SIZE), TOTAL_ELEMENTS);

    @Test
    public void mapToPageResponse_success() {

        PageResponse<CityDto> actual = mapper.mapToPageResponse(page);

        assertEquals(TOTAL_ELEMENTS, actual.totalElements());
        assertEquals(TOTAL_PAGES, actual.totalPages());
        assertEquals(PAGE, actual.currentPage());
        assertEquals(TOTAL_ELEMENTS, actual.data().size());
        assertEquals(actual.data().size(), 2);
        assertEquals(actual.data().get(0).url(), CITY_1.getUrl());
        assertEquals(actual.data().get(0).name(), CITY_1.getName());
        assertEquals(actual.data().get(1).url(), CITY_2.getUrl());
        assertEquals(actual.data().get(1).name(), CITY_2.getName());
    }
}
