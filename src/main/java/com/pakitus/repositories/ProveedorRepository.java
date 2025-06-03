package com.pakitus.repositories;

import com.pakitus.entities.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {

    //Encontrar proveedores activos
    List<Proveedor> findByActivoTrue();

    //Encontrar proveedores inactivos
    List<Proveedor> findByActivoFalse();
}