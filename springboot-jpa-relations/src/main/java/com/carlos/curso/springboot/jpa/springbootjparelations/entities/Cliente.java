package com.carlos.curso.springboot.jpa.springbootjparelations.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String secondname;

    //El primero hace referencia a el campo en el que se esta trabajando
    //One = cliente porque estamos en cliente
    //Many = Segundo campo en este caso direccion
    //Cascade hace que cuando se crea/elimina un cliente se guardan las direccion en conjunto
    //orpahnremoval hace que cuando se elimine el cliente de la tabla las direcciones que no sirven
    // se borren tambien
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    //por defecto jpa te hace una tabla intermedia llamada cliente_direcciones en este caso
    //si quieres que la llave foranea forme parte de la tabla direcciones tienes que hacer un @joincolumn
    //se consideran buenas pracfticas hacerlo con una tabla intermedia
    private Set<Direccion> direcciones;

    //esta relacion es bidireccional
    //mappedby establece la relacion inversa
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "client")
    //hay que cambiar de list a set por un tema de eficiencia en las busquedas
    //si se hace doble fetch en una query no se puede ejecutar con una lista
    private Set<Factura> facturas;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "cliente")
    private ClientDetails clientDetails;

    //es necesario inicializar la lista
    public Cliente() {
        direcciones = new HashSet<>();
        facturas = new HashSet<>();
    }

    //llamamos al constructor vacio con this() que a su vez inicializa direccion
    public Cliente(String nombre, String secondname) {
        this();
        this.nombre = nombre;
        this.secondname = secondname;
    }


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getSecondname() {
        return secondname;
    }
    public void setSecondname(String secondname) {
        this.secondname = secondname;
    }


    @Override
    public String toString() {
        return "{id=" + id + 
        ", nombre=" + nombre 
        + ", secondname=" + secondname 
        + ", facturas=" + facturas 
        + ", direcciones "+ direcciones 
        + " clientDetails" + clientDetails + "}";
    }


    public Set<Direccion> getDirecciones() {
        return direcciones;
    }


    public void setDirecciones(Set<Direccion> direcciones) {
        this.direcciones = direcciones;
    }

    public Set<Factura> getFacturas() {
        return facturas;
    }

    public void setFacturas(Set<Factura> facturas) {
        this.facturas = facturas;
    }

    public ClientDetails getClientDetails() {
        return clientDetails;
    }

    public void setClientDetails(ClientDetails clientDetails) {
        this.clientDetails = clientDetails;
        clientDetails.setCliente(this);
    }
    
     public void removeClientDetails(ClientDetails clientDetails) {
        clientDetails.setCliente(null);
        this.clientDetails = null;
        
    }

}
