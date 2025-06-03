package com.pakitus.repositories;

import com.pakitus.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    // spring boot implementa métodos básicos:
    // findAll(), findById(), save(), delete(), etc...


    // métodos derivados

    // encontrar por nombre ignorando la capitalización
    Producto findByNombreIgnoreCase(String nombre);

    // encontrar por precio menor que
    List<Producto> findByPrecioLessThan(Double precio);

    // existe por nombre ignorando la capitalización
    boolean existsByNombreIgnoreCase(String nombre);

    // contar por stock
    long countByStock(Integer stock);

    // encontrar productos por nombre de categoría
    List<Producto> findByCategoria_Nombre(String nombre);

    // consultas JPQL

    // encontrar por precio entre
    @Query("select p from Producto p where p.precio between :precioStart and :precioEnd")
    List<Producto> findByPrecioBetween(Double precioStart, Double precioEnd);

    List<Producto> findByCategoria_Id(Long id);

    List<Producto> findAllByDisponibleTrue();

    // contar productos por categoría
    long countByCategoria_Id(Long id);

    List<Producto> findByNombreContainsIgnoreCase(String nombre);



    // si la propiedad no existe entonces dará un fallo en el arranque
    // List<Producto> findByUsuario_Id(Long id);


    // @Query("select p from Producto p where p.precio between :precioMinimo and :precioMaximo")
    // List<Producto> findByPrecioBetween(@Param("precioMinimo") Double min, @Param("precioMaximo") Double max);



}