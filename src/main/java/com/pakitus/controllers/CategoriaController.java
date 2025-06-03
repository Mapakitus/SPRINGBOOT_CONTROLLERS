package com.pakitus.controllers;

import com.pakitus.entities.Categoria;
import com.pakitus.entities.Producto;
import com.pakitus.repositories.CategoriaRepository;
import com.pakitus.repositories.ProductoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class CategoriaController {

    private final CategoriaRepository categoriaRepository;
    private final ProductoRepository productoRepository;

    public CategoriaController(CategoriaRepository categoriaRepository,
                               ProductoRepository productoRepository) {
        this.categoriaRepository = categoriaRepository;
        this.productoRepository = productoRepository;
    }

    // Aquí puedes agregar métodos para manejar las operaciones CRUD de la entidad Categoria
    // Por ejemplo, un método para listar todas las categorías
    @GetMapping("/categorias")
    public String findAll(Model model) {
        List<Categoria> categorias = categoriaRepository.findAll();
        model.addAttribute("categorias", categoriaRepository.findAll());
        return "categoria/categoria-list"; // nombre del archivo HTML que muestra la lista de categorías
    }

    @GetMapping("/categorias/{id}") // http://localhost:8080/categorias/1
    public String findById(Model model, @PathVariable Long id) {
        Optional<Categoria> categoriaOpt = categoriaRepository.findById(id);

        if (categoriaOpt.isPresent()) {
            model.addAttribute("categoria", categoriaOpt.get());
        } else {
            model.addAttribute("error", "404 Categoria Not Found");
        }
        return "categoria/categoria-detail";
    }

    // mostrar formulario para crear nueva categoría
    @GetMapping("/categorias/nueva")
    public String createForm(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "categoria/categoria-form"; // nombre del archivo HTML que muestra el formulario de creación
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
        return "categoria/categoria-form"; // nombre del archivo HTML que muestra el formulario de edición
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
        if (productoRepository.countByCategoria_Id(id) > 0) {
            // Si hay productos asociados a la categoría, no se puede eliminar
            return "redirect:/categorias?error=true"; // redirigir con un mensaje de error
        }else {
            categoriaRepository.deleteById(id);// Si no hay productos asociados, se elimina la categoría
        }

        return "redirect:/categorias"; // redirigir a la lista de categorías después de eliminar
  }

    // Filtrar categorias por nombre
    @GetMapping("/categorias/buscar") // http://localhost:8080/categorias/buscar?nombre=lacteos
    public String findByNombre(Model model, @RequestParam("nombre") String nombre) {
        List<Categoria> categorias = categoriaRepository.findByNombreContainsIgnoreCase(nombre);

        model.addAttribute("categorias", categorias);
        model.addAttribute("busquedaActual", nombre); // para mostrar el término de búsqueda en la vista

        return "categoria/categoria-list"; // nombre del archivo HTML que muestra la lista de categorías
    }


}
