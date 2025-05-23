package com.pakitus.controllers;

import com.pakitus.entities.Categoria;
import com.pakitus.entities.Producto;
import com.pakitus.repositories.CategoriaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class CategoriaController {

    private CategoriaRepository categoriaRepository;

    public CategoriaController(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    // Aquí puedes agregar métodos para manejar las operaciones CRUD de la entidad Categoria
    // Por ejemplo, un método para listar todas las categorías
    @GetMapping("/categorias")
    public String findAll(Model model) {
        model.addAttribute("categorias", categoriaRepository.findAll());
        return "categoria-list"; // nombre del archivo HTML que muestra la lista de categorías
    }

    @GetMapping("/categorias/{id}") // http://localhost:8080/categorias/1
    public String findById(Model model, @PathVariable Long id) {
        Optional<Categoria> categoriaOpt = categoriaRepository.findById(id);

        if (categoriaOpt.isPresent()) {
            model.addAttribute("categoria", categoriaOpt.get());
        } else {
            model.addAttribute("error", "404 Categoria Not Found");
        }
        return "categoria-detail";
    }

    // mostrar formulario para crear nueva categoría
    @GetMapping("/categorias/nueva")
    public String createForm(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "categoria-form"; // nombre del archivo HTML que muestra el formulario de creación
    }

    // mostrar formulario para editar categoría existente
    @GetMapping("/categorias/{id}/editar")
    public String editForm(Model model, @PathVariable Long id) {
        Optional<Categoria> categoriaOpt = categoriaRepository.findById(id);

        if (categoriaOpt.isPresent()) {
            model.addAttribute("categoria", categoriaOpt.get());
        } else {
            model.addAttribute("error", "404 Categoria Not Found");
        }
        return "categoria-form"; // nombre del archivo HTML que muestra el formulario de edición
    }

    // procesar formulario (crear o actualizar)
    @PostMapping("/categorias")
    public String saveForm(@ModelAttribute Categoria categoria) {
        categoriaRepository.save(categoria);
        return "redirect:/categorias"; // redirigir a la lista de categorías después de guardar
    }

    // eliminar categoría
    @PostMapping("/categorias/{id}/eliminar")
    public String delete(@PathVariable Long id) {
        categoriaRepository.deleteById(id);

        return "redirect:/categorias"; // redirigir a la lista de categorías después de eliminar
    }
}
