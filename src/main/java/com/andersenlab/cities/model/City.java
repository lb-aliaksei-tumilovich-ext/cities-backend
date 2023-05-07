package com.andersenlab.cities.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 *city
 *
 *@author Aliaksei Tumilovich
 *01.03.2023
 */
@Entity
@Getter
@Setter
@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="cities", schema="public")
public class City implements Identifiable<Long>{

    @Id
    @GeneratedValue(generator = "cities_seq_generator")
    @SequenceGenerator(name = "cities_seq_generator",
       sequenceName = "cities_seq", allocationSize = 1, initialValue = 1001)
    private Long id;

    @Column(name = "name", nullable = false, columnDefinition = "text", length = 100)
    private String name;

    @Column(name = "url", nullable = false, columnDefinition = "text", length = 800)
    private String url;

    @Override
    public Long getById() {
        return id;
    }
}
