package com.pakitus;

import com.pakitus.entities.Producto;
import com.pakitus.repositories.CategoriaRepository;
import com.pakitus.repositories.ProductoRepository;
import com.pakitus.repositories.ProveedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class TestOperaciones {
    private final ProductoRepository productoRepository;

    public void ejecutarPruebas() {

        // probar métodos y consultas

        // probar Producto findByNombreIgnoreCase(String nombre)
        String nombreBuscado = "manzana"; // en minúscula
        Producto productoEncontrado = productoRepository.findByNombreIgnoreCase(nombreBuscado);
        if (productoEncontrado != null) {
            System.out.println("Producto encontrado por nombre '" + nombreBuscado +
                    "': " + productoEncontrado);
        } else {
            System.out.println("No se encontró ningún producto con el nombre: " + nombreBuscado);
        }

        // probar List<Producto> findByPrecioLessThan(Double precio)
        Double precioMaximo = 3.00;
        List<Producto> productosBaratos = productoRepository.findByPrecioLessThan(precioMaximo);
        System.out.println("Productos con precio menor que " + precioMaximo + ":");
        for (Producto producto : productosBaratos) {
            System.out.println("\t " + producto.getNombre() + ": " + producto.getPrecio() + " euros");
        }

        // probar boolean existsByNombreIgnoreCase(String nombre)
        String nombreExistente = "YOGUR";
        boolean existeProducto = productoRepository.existsByNombreIgnoreCase(nombreExistente);
        System.out.println("¿Existe un producto llamado '" + nombreExistente + "'? " + existeProducto);
        String nombreNoExistente = "pera";
        boolean noExisteProducto = productoRepository.existsByNombreIgnoreCase(nombreNoExistente);
        System.out.println("¿Existe un producto llamado '" + nombreNoExistente + "'? " +
                (noExisteProducto ? "Sí" : "No"));

        // probar long countByStock(Integer stock)
        Integer stockBuscado = 100;
        long cantidadProductos = productoRepository.countByStock(stockBuscado);
        System.out.println("Cantidad de productos con stock de " + stockBuscado +
                ": " + cantidadProductos);

        // List<Producto> findByPrecioBetween(Double precioStart, Double precioEnd)
        Double rangoPrecioMinimo = 1.00;
        Double rangoPrecioMaximo = 4.00;
        List<Producto> productosEnRango = productoRepository.findByPrecioBetween(rangoPrecioMinimo, rangoPrecioMaximo);

        System.out.println("Productos con precio entre " + rangoPrecioMinimo + " euros y " +
                rangoPrecioMaximo + " euros:");
        if (productosEnRango.isEmpty()) {
            System.out.println("No se han encontrado productos en este rango de precio.");
        } else {
            for (Producto producto : productosEnRango) {
                System.out.println(producto.getNombre() + ": " + producto.getPrecio() + " euros");
            }
        }

        System.out.println("Total de productos en el rango: " + productosEnRango.size());

        // probar List<Producto> findByCategoria_Nombre(String nombre)
        String categoriaBuscada = "Frutas";
        List<Producto> productosEnCategoria = productoRepository.findByCategoria_Nombre(categoriaBuscada);
        System.out.println("Productos en la categoría " + categoriaBuscada + ":");
        if (productosEnCategoria.isEmpty()) {
            System.out.println("No se han encontrado productos en la categoría " + categoriaBuscada);
        } else {
            for (Producto producto : productosEnCategoria) {
                System.out.println("- " + producto.getNombre() + " (" + producto.getCategoria().getNombre() + ")" + ": " + producto.getPrecio() + " euros");
            }
        }


    }

}
