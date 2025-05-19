package com.pakitus;

import com.pakitus.entities.Automovil;
import com.pakitus.repositories.AutomovilRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {

		// inicializar spring
		ApplicationContext spring = SpringApplication.run(Main.class, args);

		// obtener repositorio (lo crea spring)
		AutomovilRepository automovilRepository = spring.getBean(AutomovilRepository.class);

		//crear automoviles
		Automovil automovil1 = new Automovil("XTRAIL", "Nissan", 160, 40000.0, true);
		Automovil automovil2 = new Automovil("CIVIC", "Honda", 150, 38000.0, false);
		Automovil automovil3 = new Automovil("COROLLA", "Toyota", 140, 35000.0, true);
		Automovil automovil4 = new Automovil("FIESTA", "Ford", 120, 25000.0, false);
		Automovil automovil5 = new Automovil("MUSTANG", "Ford", 300, 60000.0, true);

		// guardar automoviles
		automovilRepository.saveAll(List.of(automovil1, automovil2, automovil3, automovil4, automovil5));

		System.out.println("Automoviles guardados: " + automovilRepository.findAll());
		System.out.println("Número total de automoviles: " + automovilRepository.count());

		// probar métodos y consultas
		// Un método derivado para buscar automóviles con caballos mayor que un valor dado
		// List<Automovil> findByCaballosGreaterThan(Integer caballos);
		Integer caballosMin = 150;
		List<Automovil> automovilesPotentes = automovilRepository.findByCaballosGreaterThan(caballosMin);
		System.out.println("Automoviles con caballos mayores que " + caballosMin + ":");
		for (Automovil automovil : automovilesPotentes) {
			System.out.println(automovil.getMarca() + " " + automovil.getModelo() + ":" +
					automovil.getCaballos() + " CV");
		}

		//Un método derivado para encontrar automóviles por modelo ignorando mayúsculas y minúsculas
		//List<Automovil> findByModeloIgnoreCase(String modelo);
		String modeloBuscado = "civic";
		List<Automovil> automovilesModelo = automovilRepository.findByModeloIgnoreCase(modeloBuscado);
		if (!automovilesModelo.isEmpty()) {
			System.out.println("Automoviles encontrados por modelo '" + modeloBuscado + "':");
			for (Automovil automovil : automovilesModelo) {
				System.out.println(automovil.getMarca() + " " + automovil.getModelo());
			}
		} else {
			System.out.println("No se encontró ningún automovil con el modelo: " + modeloBuscado);
		}

		//Un método derivado para encontrar automóviles según si son eléctricos o no
		//List<Automovil> findByElectrico(Boolean electrico);
		Boolean esElectrico = true; // Cambia a false si quieres buscar no eléctricos
		List<Automovil> automovilesElectricos = automovilRepository.findByElectrico(esElectrico);

		if (!automovilesElectricos.isEmpty()) {
			System.out.println("Automóviles " + (esElectrico ? "eléctricos" : "no eléctricos") + " encontrados:");
			for (Automovil automovil : automovilesElectricos) {
				System.out.println(automovil.getMarca() + " " + automovil.getModelo());
			}
		} else {
			System.out.println("No se encontraron automóviles " + (esElectrico ? "eléctricos" : "no eléctricos") + ".");
		}

		//Una consulta JPQL que encuentre todos los automóviles en un rango entre dos precios
		//@Query("select a from Automovil a where a.precio between ?1 and ?2")
		//List<Automovil> findByPrecioBetween(Double precioStart, Double precioEnd);
		Double precioMinimo = 30000.0;
		Double precioMaximo = 50000.0;
		List<Automovil> automovilesEnRango = automovilRepository.findByPrecioBetween(precioMinimo, precioMaximo);
		if (!automovilesEnRango.isEmpty()) {
			System.out.println("Automóviles encontrados en el rango de precios entre " + precioMinimo + " y " + precioMaximo + ":");
			for (Automovil automovil : automovilesEnRango) {
				System.out.println(automovil.getMarca() + " " + automovil.getModelo() + ": " +
						automovil.getPrecio() + " euros");
			}
		} else {
			System.out.println("No se encontraron automóviles en el rango de precios entre " + precioMinimo + " y " + precioMaximo + ".");
		}



	}

}
