package com.restapi.superhero.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restapi.superhero.entity.Superhero;

public interface SuperheroRepository extends JpaRepository<Superhero, Long> {

}
