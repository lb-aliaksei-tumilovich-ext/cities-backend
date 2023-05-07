package com.andersenlab.cities.repository;

import com.andersenlab.cities.model.City;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


/**
 *CityRepository
 *
 *@author Aliaksei Tumilovich
 *01.03.2023
 */
public interface CityRepository extends JpaRepository<City, Long>, JpaSpecificationExecutor<City> {
    Page<City> findAllByNameContainsIgnoreCase(Pageable pageable, String name);
}
