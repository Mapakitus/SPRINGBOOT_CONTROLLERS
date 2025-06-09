package com.pakitus.controllers;

import com.pakitus.entities.Producto;
import com.pakitus.entities.Proveedor;
import com.pakitus.repositories.CategoriaRepository;
import com.pakitus.repositories.ProductoRepository;
import com.pakitus.repositories.ProveedorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
public class ProductoController {

    private ProductoRepository productoRepository;
    private CategoriaRepository categoriaRepository;
    private ProveedorRepository proveedorRepository;

    public ProductoController(ProductoRepository productoRepository, CategoriaRepository categoriaRepository, ProveedorRepository proveedorRepository) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
        this.proveedorRepository = proveedorRepository;
    }

    // anotación que mapea las peticiones GET a la URL "/productos"
    @GetMapping("/productos") // http://localhost:8080/productos
    public String findAll(Model model) {
// crear una lista con todos los productos
        List<Producto> productos = productoRepository.findAll();
        model.addAttribute("productos", productos);
        model.addAttribute("categorias", categoriaRepository.findAll());


        return "producto/producto-list";
    }

    @GetMapping("/productos/{id}") // http://localhost:8080/productos/1
    public String findById(Model model, @PathVariable Long id) {
        Optional<Producto> productoOpt = productoRepository.findById(id);

        if (productoOpt.isPresent()) {
            model.addAttribute("producto", productoOpt.get());
        } else {
            model.addAttribute("error", "404 Producto Not Found");
        }

        return "producto/producto-detail";
    }

    // mostrar formulario para crear nuevo producto
    @GetMapping("/productos/nuevo")
    public String createForm(Model model) {
        model.addAttribute("producto", new Producto());
        model.addAttribute("categorias", categoriaRepository.findAll());

        return "producto/producto-form";
    }

    // mostrar formulario para editar producto existente
    @GetMapping("/productos/{id}/editar")
    public String editForm(Model model, @PathVariable Long id) {
        Optional<Producto> productoOpt = productoRepository.findById(id);

        if (productoOpt.isPresent()) {
            model.addAttribute("producto", productoOpt.get());
            model.addAttribute("categorias", categoriaRepository.findAll());
        } else {
            model.addAttribute("error", "Producto no encontrado");
        }

        return "producto/producto-form";
    }

    // procesar formulario (crear o actualizar)
    @PostMapping("/productos") // podría ser @PostMapping("/productos/form") si en el formulario pusiera th:action="@{/productos/form}"
    public String saveForm(@ModelAttribute Producto producto) {
        productoRepository.save(producto);

        return "redirect:/productos";
    }

    // eliminar producto (con funciones lambda)
    @PostMapping("/productos/{id}/eliminar")
    public String delete(@PathVariable Long id) {
        Optional<Producto> productoOpt = productoRepository.findById(id);

        if (productoOpt.isPresent()) {
// buscar proveedores que tienen este producto
            List<Proveedor> proveedores = proveedorRepository.findByProductos_Id(id);

// desvincular producto de todos los proveedores
            proveedores.forEach(proveedor -> {
                proveedor.getProductos().removeIf(producto -> producto.getId().equals(id));
                proveedorRepository.save(proveedor);
            });

// eliminar el producto
            productoRepository.deleteById(id);
        }

        return "redirect:/productos";
    }

    // eliminar producto
/*@PostMapping("/productos/{id}/eliminar")
public String delete(@PathVariable Long id) {
// buscar el producto
Optional<Producto> productoOpt = productoRepository.findById(id);

if (productoOpt.isPresent()) {
// buscar proveedores que tienen este producto
List<Proveedor> proveedores = proveedorRepository.findByProductos_Id(id);

// desvincular el producto de cada proveedor
for (Proveedor proveedor : proveedores) {
// crear una lista de productos a mantener
List<Producto> productosMantener = new ArrayList<>();

for (Producto producto : proveedor.getProductos()) {
if (!producto.getId().equals(id)) { // si NO es el producto a eliminar
productosMantener.add(producto);
}
}

// reemplazar la lista
proveedor.setProductos(productosMantener);
proveedorRepository.save(proveedor);
}

// borrar el producto
productoRepository.deleteById(id);
}

return "redirect:/productos";
}*/

    //Filtros
    //Filtrar productos por categoria
    @GetMapping("/productos/categoria/{categoriaId}")
    public String findByCategoria(Model model, @PathVariable Long categoriaId) {
        List<Producto> productos = productoRepository.findByCategoria_Id(categoriaId);
        model.addAttribute("productos", productos);
        model.addAttribute("categorias", categoriaRepository.findAll());
        return "producto/producto-list"; // nombre del archivo HTML que muestra la lista de productos filtrados
    }

    // Filtrar productos por nombre
    @GetMapping("/productos/buscar") // http://localhost:8080/productos/buscar?nombre=manzana
    public String findByNombre(Model model, @RequestParam("nombre") String nombre) {
        List<Producto> productos = productoRepository.findByNombreContainsIgnoreCase(nombre);
        model.addAttribute("productos", productos);
        model.addAttribute("categorias", categoriaRepository.findAll());
        model.addAttribute("busquedaActual", nombre); // para mostrar el término de búsqueda en la vista

        return "producto/producto-list"; // nombre del archivo HTML que muestra la lista de productos filtrados por nombre
    }

}