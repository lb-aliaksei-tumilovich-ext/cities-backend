package com.andersenlab.cities.integrations;

import com.andersenlab.cities.dto.CityDto;
import com.andersenlab.cities.dto.PageResponse;
import com.andersenlab.cities.dto.UpdateCityRequest;
import com.andersenlab.cities.exception.dto.ErrorResponse;
import com.andersenlab.cities.exception.enums.BadRequestErrorEnum;
import com.andersenlab.cities.model.City;
import com.andersenlab.cities.repository.CityRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *CityIT
 *
 *@author Aliaksei Tumilovich
 *02.03.2023
 */
public class CityControllerIT extends AbstractIT {

    public static final int ZERO = 0;
    public static final int DEFAULT_TOTAL_ELEMENTS = 1000;
    public static final int DEFAULT_TOTAL_PAGES = 50;
    public static final int DEFAULT_PAGE_SIZE = 20;
    public static final String CITIES_URL = "/cities";
    public static final String PUT_CITIES_URL = "/cities/";
    public static final String PAGE_PARAM = "page";
    public static final String SIZE_PARAM = "size";
    public static final String PAGE_VALUE = "3";
    public static final String SIZE_VALUE = "5";
    public static final String NEGATIVE_VALUE = "-1";
    public static final int BY_PAGE_AND_SIZE_TOTAL_PAGES = 200;
    public static final String NAME_PARAM = "name";
    public static final String NAME_VALUE = "Del";
    public static final String LONG_VALUE = "DelisaRa";
    public static final int BY_PAGE_SIZE_AND_NAME_TOTAL_PAGES = 2;
    public static final int BY_PAGE_SIZE_AND_NAME_TOTAL_ELEMENTS = 8;
    public static final String NEW_URL = "https://andersenlab.com/main.jpg";
    public static final String NEW_NAME = "newName";
    public static final UpdateCityRequest UPDATE_CITY_REQUEST = UpdateCityRequest.builder()
       .url(NEW_URL)
       .name(NEW_NAME)
       .build();

    public static final UpdateCityRequest UPDATE_CITY_BAD_REQUEST = UpdateCityRequest.builder()
       .url("wrong url")
       .name("")
       .build();
    public static final String CITY_ID = "1";

    public static final String GET_CITY_ID = "124";
    public static final Long GET_CITY_ID_LONG = 124L;
    public static final Long CITY_ID_LONG = 1L;
    public static final String WRONG_CITY_ID = "10002";
    public static final String ACCESS_CREDENTIALS = "Basic ZWRpdG9yOnBhc3N3b3Jk";
    public static final String CITY_NAME = "Fuzhou";
    public static final String CITY_URL = "https://upload.wikimedia.org/wikipedia/commons/9/9d/Foochow_pic.PNG";

    @Autowired
    MockMvc mockMvc;

    @Autowired CityRepository cityRepository;

    @Test
    public void getCities_success() throws Exception {

        PageResponse<List<CityDto>> actualResponse = OBJECT_MAPPER.readValue(mockMvc.perform(get(CITIES_URL)
              .contentType(MediaType.APPLICATION_JSON)
              .header(HttpHeaders.AUTHORIZATION, ACCESS_CREDENTIALS))
           .andDo(print())
           .andExpect(status().is(HttpStatus.OK.value()))
           .andReturn().getResponse().getContentAsString(), OBJECT_MAPPER.getTypeFactory().constructParametricType(PageResponse.class, CityDto.class));

        assertEquals(actualResponse.currentPage(), ZERO);
        assertEquals(actualResponse.totalElements(), DEFAULT_TOTAL_ELEMENTS);
        assertEquals(actualResponse.totalPages(), DEFAULT_TOTAL_PAGES);
        assertEquals(actualResponse.data().size(), DEFAULT_PAGE_SIZE);
    }

    @Test
    public void getCities_byPage_and_bySize_success() throws Exception {

        PageResponse<List<CityDto>> actualResponse = OBJECT_MAPPER.readValue(mockMvc.perform(get(CITIES_URL)
              .param(PAGE_PARAM, PAGE_VALUE)
              .param(SIZE_PARAM, SIZE_VALUE)
              .contentType(MediaType.APPLICATION_JSON)
              .header(HttpHeaders.AUTHORIZATION, ACCESS_CREDENTIALS))
           .andDo(print())
           .andExpect(status().is(HttpStatus.OK.value()))
           .andReturn().getResponse().getContentAsString(), OBJECT_MAPPER.getTypeFactory().constructParametricType(PageResponse.class, CityDto.class));

        assertEquals(actualResponse.currentPage(), Integer.parseInt(PAGE_VALUE));
        assertEquals(actualResponse.totalElements(), DEFAULT_TOTAL_ELEMENTS);
        assertEquals(actualResponse.totalPages(), BY_PAGE_AND_SIZE_TOTAL_PAGES);
        assertEquals(actualResponse.data().size(), Integer.parseInt(SIZE_VALUE));
    }

    @Test
    public void getCities_byPage_and_bySize_and_byName_success() throws Exception {

        PageResponse<List<CityDto>> actualResponse = OBJECT_MAPPER.readValue(mockMvc.perform(get(CITIES_URL)
              .param(PAGE_PARAM, String.valueOf(ZERO))
              .param(SIZE_PARAM, SIZE_VALUE)
              .param(NAME_PARAM, NAME_VALUE)
              .contentType(MediaType.APPLICATION_JSON)
              .header(HttpHeaders.AUTHORIZATION, ACCESS_CREDENTIALS))
           .andDo(print())
           .andExpect(status().is(HttpStatus.OK.value()))
           .andReturn().getResponse().getContentAsString(), OBJECT_MAPPER.getTypeFactory().constructParametricType(PageResponse.class, CityDto.class));

        assertEquals(actualResponse.currentPage(), ZERO);
        assertEquals(actualResponse.totalElements(), BY_PAGE_SIZE_AND_NAME_TOTAL_ELEMENTS);
        assertEquals(actualResponse.totalPages(), BY_PAGE_SIZE_AND_NAME_TOTAL_PAGES);
        assertEquals(actualResponse.data().size(), Integer.parseInt(SIZE_VALUE));
    }

    @Test
    public void getCities_byPage_and_bySize_and_byLongName_success() throws Exception {

        PageResponse<List<CityDto>> actualResponse = OBJECT_MAPPER.readValue(mockMvc.perform(get(CITIES_URL)
              .param(PAGE_PARAM, String.valueOf(ZERO))
              .param(SIZE_PARAM, SIZE_VALUE)
              .param(NAME_PARAM, LONG_VALUE)
              .contentType(MediaType.APPLICATION_JSON)
              .header(HttpHeaders.AUTHORIZATION, ACCESS_CREDENTIALS))
           .andDo(print())
           .andExpect(status().is(HttpStatus.OK.value()))
           .andReturn().getResponse().getContentAsString(), OBJECT_MAPPER.getTypeFactory().constructParametricType(PageResponse.class,
           OBJECT_MAPPER.getTypeFactory().constructParametricType(List.class, CityDto.class)));

        assertEquals(actualResponse.currentPage(), ZERO);
        assertEquals(actualResponse.totalElements(), ZERO);
        assertEquals(actualResponse.totalPages(), ZERO);
        assertEquals(actualResponse.data().size(), ZERO);
    }

    @Test
    public void getCities_bad_request() throws Exception {

        ErrorResponse actualResponse = OBJECT_MAPPER.readValue(mockMvc.perform(get(CITIES_URL)
              .param(SIZE_PARAM, NEGATIVE_VALUE)
              .param(NAME_PARAM, NEGATIVE_VALUE)
              .contentType(MediaType.APPLICATION_JSON)
              .header(HttpHeaders.AUTHORIZATION, ACCESS_CREDENTIALS))
           .andDo(print())
           .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
           .andReturn().getResponse().getContentAsString(), ErrorResponse.class);

        assertEquals(actualResponse.getErrorCode(), String.valueOf(HttpStatus.BAD_REQUEST.value()));

    }

    @Test
    public void updateCity_success() throws Exception {

        CityDto cityFromResponse = OBJECT_MAPPER.readValue(mockMvc.perform(put(PUT_CITIES_URL + CITY_ID)
              .content(OBJECT_MAPPER.writeValueAsString(UPDATE_CITY_REQUEST))
              .contentType(MediaType.APPLICATION_JSON)
              .header(HttpHeaders.AUTHORIZATION, ACCESS_CREDENTIALS))
           .andDo(print())
           .andExpect(status().is(HttpStatus.OK.value())
           ).andReturn().getResponse().getContentAsString(), CityDto.class);
        Optional<City> optionalSavedCity = cityRepository.findById(CITY_ID_LONG);

        assertEquals(cityFromResponse.id(), CITY_ID_LONG);
        assertEquals(cityFromResponse.name(), UPDATE_CITY_REQUEST.name());
        assertEquals(cityFromResponse.url(), UPDATE_CITY_REQUEST.url());
        assertTrue(optionalSavedCity.isPresent());
        City savedCity = optionalSavedCity.get();
        assertEquals(savedCity.getId(), CITY_ID_LONG);
        assertEquals(savedCity.getName(), UPDATE_CITY_REQUEST.name());
        assertEquals(savedCity.getUrl(), UPDATE_CITY_REQUEST.url());

    }

    @Test
    public void updateCity_unauthorized() throws Exception {

        mockMvc.perform(put(PUT_CITIES_URL + CITY_ID)
              .content(OBJECT_MAPPER.writeValueAsString(UPDATE_CITY_REQUEST))
              .contentType(MediaType.APPLICATION_JSON))
           .andDo(print())
           .andExpect(status().is(HttpStatus.UNAUTHORIZED.value()));

    }

    @Test
    public void updateCity_bad_request() throws Exception {

        ErrorResponse actualResponse = OBJECT_MAPPER.readValue(mockMvc.perform(put(PUT_CITIES_URL + WRONG_CITY_ID)
              .content(OBJECT_MAPPER.writeValueAsString(UPDATE_CITY_BAD_REQUEST))
              .contentType(MediaType.APPLICATION_JSON)
              .header(HttpHeaders.AUTHORIZATION, ACCESS_CREDENTIALS))
           .andDo(print())
           .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
           .andReturn()
           .getResponse()
           .getContentAsString(), ErrorResponse.class);

        assertEquals(actualResponse.getErrorCode(), String.valueOf(HttpStatus.BAD_REQUEST.value()));
    }

    @Test
    public void updateCity_validation_error() throws Exception {

        ErrorResponse actualResponse = OBJECT_MAPPER.readValue(mockMvc.perform(put(PUT_CITIES_URL + CITY_ID)
              .content(OBJECT_MAPPER.writeValueAsString(UPDATE_CITY_BAD_REQUEST))
              .contentType(MediaType.APPLICATION_JSON)
              .header(HttpHeaders.AUTHORIZATION, ACCESS_CREDENTIALS))
           .andDo(print())
           .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
           .andReturn().getResponse().getContentAsString(), ErrorResponse.class);

        assertEquals(actualResponse.getErrorCode(), String.valueOf(HttpStatus.BAD_REQUEST.value()));
    }

    @Test
    public void getCity_success() throws Exception {

        CityDto actualResponse = OBJECT_MAPPER.readValue(mockMvc.perform(get(CITIES_URL + "/" + GET_CITY_ID)
              .contentType(MediaType.APPLICATION_JSON)
              .header(HttpHeaders.AUTHORIZATION, ACCESS_CREDENTIALS))
           .andDo(print())
           .andExpect(status().is(HttpStatus.OK.value()))
           .andReturn().getResponse().getContentAsString(), CityDto.class);

        assertEquals(actualResponse.id(), GET_CITY_ID_LONG);
        assertEquals(actualResponse.name(), CITY_NAME);
        assertEquals(actualResponse.url(), CITY_URL);
    }

    @Test
    public void getCity_throws() throws Exception {

        ErrorResponse actualResponse = OBJECT_MAPPER.readValue(mockMvc.perform(get(CITIES_URL + "/" + WRONG_CITY_ID)
              .contentType(MediaType.APPLICATION_JSON)
              .header(HttpHeaders.AUTHORIZATION, ACCESS_CREDENTIALS))
           .andDo(print())
           .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
           .andReturn().getResponse().getContentAsString(), ErrorResponse.class);

        assertEquals(actualResponse.getErrorCode(),BadRequestErrorEnum.CITY_NOT_EXISTS.getErrorCode());
        assertEquals(actualResponse.getErrorMessage(), BadRequestErrorEnum.CITY_NOT_EXISTS.getMessage());
    }

}
