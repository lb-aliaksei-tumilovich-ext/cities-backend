package com.andersenlab.cities.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *PageRespDto
 *
 *@author Aliaksei Tumilovich
 *01.03.2023
 */
@Builder
public record PageResponse<T> (List<T> data, Integer currentPage, Long totalElements,  Integer totalPages) {}

