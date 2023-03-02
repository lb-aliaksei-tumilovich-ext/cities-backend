package com.andersenlab.cities;

import com.andersenlab.cities.dto.UpdateCityRequest;
import com.andersenlab.cities.model.City;

import java.util.List;

/**
 *AbstractTest
 *
 *@author Aliaksei Tumilovich
 *06.03.2023
 */
public abstract class AbstractTest {
    public static final City CITY_1 = City.builder()
       .id(1L)
       .name("city1")
       .url("url1")
       .build();

    public static final UpdateCityRequest UPDATE_CITY = UpdateCityRequest.builder()
       .name(CITY_1.getName())
       .url(CITY_1.getUrl())
       .build();

    public static final City CITY_2 = City.builder()
       .id(2L)
       .name("city2")
       .url("url2")
       .build();
    public static final List<City> CITIES = List.of(CITY_1, CITY_2);
}
