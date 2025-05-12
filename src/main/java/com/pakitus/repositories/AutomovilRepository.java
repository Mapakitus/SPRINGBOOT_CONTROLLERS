package com.pakitus.repositories;

import com.pakitus.entities.Automovil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AutomovilRepository extends JpaRepository<Automovil, Long> {

    // Métodos derivados
    //Un método derivado para buscar automóviles con caballos mayor que un valor dado
    List<Automovil> findByCaballosGreaterThan(Integer caballos);

    //Un método derivado para encontrar automóviles por modelo ignorando mayúsculas y minúsculas
    List<Automovil> findByModeloIgnoreCase(String modelo);

    //Un método derivado para encontrar automóviles según si son eléctricos o no
    List<Automovil> findByElectrico(Boolean electrico);

    //Una consulta JPQL que encuentre todos los automóviles en un rango entre dos precios
    @Query("select a from Automovil a where a.precio between ?1 and ?2")
    List<Automovil> findByPrecioBetween(Double precioStart, Double precioEnd);

}
