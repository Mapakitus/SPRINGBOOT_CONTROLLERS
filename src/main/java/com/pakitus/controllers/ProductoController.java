package com.pakitus.controllers;

import com.pakitus.entities.Producto;
import com.pakitus.repositories.CategoriaRepository;
import com.pakitus.repositories.ProductoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;


@Controller
public class ProductoController {

    private ProductoRepository productoRepository;
    private CategoriaRepository categoriaRepository;

    public ProductoController(ProductoRepository productoRepository, CategoriaRepository categoriaRepository) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    // anotación que mapea las peticiones GET a la URL "/productos"
    @GetMapping("/productos") // http://localhost:8080/productos
    public String findAll(Model model) {
// crear una lista con todos los productos
        List<Producto> productos = productoRepository.findAll();
        model.addAttribute("productos", productos);

        return "producto-list";
    }

    @GetMapping("/productos/{id}") // http://localhost:8080/productos/1
    public String findById(Model model, @PathVariable Long id) {
        Optional<Producto> productoOpt = productoRepository.findById(id);

        if (productoOpt.isPresent()) {
            model.addAttribute("producto", productoOpt.get());
        } else {
            model.addAttribute("error", "404 Producto Not Found");
        }

        return "producto-detail";
    }

    // mostrar formulario para crear nuevo producto
    @GetMapping("/productos/nuevo")
    public String createForm(Model model) {
        model.addAttribute("producto", new Producto());
        model.addAttribute("categorias", categoriaRepository.findAll());

        return "producto-form";
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

        return "producto-form";
    }

    // procesar formulario (crear o actualizar)
    @PostMapping("/productos") // podría ser @PostMapping("/productos/form") si en el formulario pusiera th:action="@{/productos/form}"
    public String saveForm(@ModelAttribute Producto producto) {
        productoRepository.save(producto);

        return "redirect:/productos";
    }

    // eliminar producto
    @PostMapping("/productos/{id}/eliminar")
    public String delete(@PathVariable Long id) {
        productoRepository.deleteById(id);

        return "redirect:/productos";
    }
}