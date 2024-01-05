package com.carlos.curso.springboot.jpa.springbootjparelations.repositories;

import org.springframework.data.repository.CrudRepository;

import com.carlos.curso.springboot.jpa.springbootjparelations.entities.ClientDetails;

public interface ClienteDetailsRepository extends CrudRepository<ClientDetails, Long> {

}
