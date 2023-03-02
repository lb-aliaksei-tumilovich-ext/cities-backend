package com.andersenlab.cities.controller;

import com.andersenlab.cities.dto.CityDto;
import com.andersenlab.cities.dto.PageResponse;
import com.andersenlab.cities.dto.UpdateCityRequest;
import com.andersenlab.cities.service.CityService;

import org.hibernate.validator.constraints.Range;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

/**
 *CityController
 *
 *@author Aliaksei Tumilovich
 *01.03.2023
 */

@RestController
@RequestMapping("/cities")
@RequiredArgsConstructor
@Validated
@Api(value = "Cities management service")
public class CityController {
    private final CityService cityService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get cities1")
    public PageResponse<CityDto> getCities(
       @RequestParam(defaultValue = "0") @Range(min = 0, message = "page_must_be_positive") int page,
       @RequestParam(defaultValue = "20") @Range(min = 0, message = "size_must_be_positive") int size,
       @RequestParam(value = "name", required = false) String name) {
        return cityService.getCities(PageRequest.of(page, size), name);
    }

    @GetMapping({ "{id}" })
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get city")
    public CityDto getCity(@PathVariable("id") Long id) {
        return cityService.getCity(id);
    }

    @PreAuthorize("hasRole('ROLE_ALLOW_EDIT')")
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Update city")
    public CityDto updateCity(@Valid @RequestBody UpdateCityRequest city, @PathVariable("id") Long id) {
        return cityService.updateCity(id, city);
    }
}
