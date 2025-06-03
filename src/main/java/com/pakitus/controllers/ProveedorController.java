package com.pakitus.controllers;

import com.pakitus.entities.Proveedor;
import com.pakitus.repositories.ProductoRepository;
import com.pakitus.repositories.ProveedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor //generar constructor automáticamente para los campos final
@Controller
public class ProveedorController {

    private final ProveedorRepository proveedorRepository;
    private final ProductoRepository productoRepository;

    //listar todos los proveedores
    @GetMapping("/proveedores")
    public String findAll(Model model) {
        //obtener todos los proveedores de la base de datos
        List<Proveedor> proveedores = proveedorRepository.findAll();
        model.addAttribute("proveedores", proveedores);

        return "/proveedor/proveedor-list"; //nombre del archivo HTML (proveedor-list.html)
    }

    // mostrar detalle de un proveedor por su id
    @GetMapping("/proveedores/{id}")
    public String findById(Model model, @PathVariable Long id) {
        //buscar proveedor por id
       Optional<Proveedor> proveedorOpt = proveedorRepository.findById(id);
        if (proveedorOpt.isPresent()) {
            model.addAttribute("proveedor", proveedorOpt.get());
        } else {
            model.addAttribute("error", "404 Proveedor Not Found");
        }

        return "/proveedor/proveedor-detail"; //nombre del archivo HTML (proveedor-detail.html)
    }

    // mostrar formulario para crear nuevo proveedor
    @GetMapping("/proveedores/nuevo")
    public String createForm(Model model) {
        model.addAttribute("proveedor", new Proveedor());
        model.addAttribute("productos", productoRepository.findAll());
        return "/proveedor/proveedor-form"; //nombre del archivo HTML (proveedor-form.html)
    }

    // mostrar formulario para editar proveedor existente
    @GetMapping("/proveedores/{id}/editar")
    public String editForm(Model model, @PathVariable Long id) {
        Optional<Proveedor> proveedorOpt = proveedorRepository.findById(id);
        if (proveedorOpt.isPresent()) {
            model.addAttribute("proveedor", proveedorOpt.get());
            model.addAttribute("productos", productoRepository.findAll());
        } else {
            model.addAttribute("error", "404 Proveedor Not Found");
        }
        return "/proveedor/proveedor-form"; //nombre del archivo HTML (proveedor-form.html)
    }

    //procesar el formulario de creación o edición de proveedor
    @PostMapping("/proveedores")
    public String saveForm(@ModelAttribute Proveedor proveedor) {
        //guardar el proveedor en la base de datos
        proveedorRepository.save(proveedor);
        return "redirect:/proveedores"; //redireccionar a la lista de proveedores
    }

    // eliminar un proveedor por su id
    @PostMapping("/proveedores/{id}/eliminar")
    public String delete(@PathVariable Long id) {
        //eliminar el proveedor por su id
        proveedorRepository.deleteById(id);
        return "redirect:/proveedores"; //redireccionar a la lista de proveedores
    }

    //FILTROS
    //flitrar por proveedor activo
    @GetMapping("/proveedores/activos")
    public String findActivos(Model model) {
        List<Proveedor> proveedores = proveedorRepository.findByActivoTrue();
        model.addAttribute("proveedores", proveedores);
        return "/proveedor/proveedor-list"; //nombre del archivo HTML (proveedor-list.html)
    }

    //Filtrar por proveedor inactivo
    @GetMapping("/proveedores/inactivos")
    public String findInactivos(Model model) {
        List<Proveedor> proveedores = proveedorRepository.findByActivoFalse();
        model.addAttribute("proveedores", proveedores);
        return "/proveedor/proveedor-list"; //nombre del archivo HTML (proveedor-list.html)
    }
}
