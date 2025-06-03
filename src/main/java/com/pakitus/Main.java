package com.pakitus;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {

		// inicializar spring
		ApplicationContext spring = SpringApplication.run(Main.class, args);

		// ejecutar pruebas (Descomentar para ejecutar las pruebas)
		//TestOperaciones testOperaciones = spring.getBean(TestOperaciones.class);
		//testOperaciones.ejecutarPruebas();

	}
}

