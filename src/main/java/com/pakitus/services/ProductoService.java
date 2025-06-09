package com.pakitus.services;
import com.pakitus.entities.Producto;
import com.pakitus.repositories.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProductoService {

    private ProductoRepository productoRepository;

    //métodos CRUD (delegación)

    public List<Producto> findAll() {
       return productoRepository.findAll(); //delegación simple
    }

    public Optional<Producto> findById(Long id) {
        return productoRepository.findById(id); //delegación simple
    }

    public Producto save(Producto producto) {
        return productoRepository.save(producto); //delegación simple
    }

    public void deleteById(Long id) {
        productoRepository.deleteById(id); //delegación simple
    }


    //Lógica de negocio

}
