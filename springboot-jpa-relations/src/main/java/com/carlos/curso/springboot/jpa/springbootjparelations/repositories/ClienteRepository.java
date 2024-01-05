package com.carlos.curso.springboot.jpa.springbootjparelations.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.carlos.curso.springboot.jpa.springbootjparelations.entities.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {

    //left hace que traiga el cliente tenga factura/direccion o no la tenga
    @Query("select c from Cliente c left join fetch c.direcciones where c.id=?1")
    Optional<Cliente> findOneWithDirecciones(Long id);

    @Query("select c from Cliente c left join fetch c.facturas  where c.id=?1")
    Optional<Cliente> findOneWithFactura(Long id);

    @Query("select c from Cliente c left join fetch c.facturas left join fetch c.direcciones left join fetch c.clientDetails  where c.id=?1")
    Optional<Cliente> findOne(Long id);
}
