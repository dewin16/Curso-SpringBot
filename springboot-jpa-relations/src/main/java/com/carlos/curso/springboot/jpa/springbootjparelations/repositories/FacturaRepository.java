package com.carlos.curso.springboot.jpa.springbootjparelations.repositories;

import org.springframework.data.repository.CrudRepository;

import com.carlos.curso.springboot.jpa.springbootjparelations.entities.Factura;

public interface FacturaRepository extends CrudRepository<Factura, Long> {

}
