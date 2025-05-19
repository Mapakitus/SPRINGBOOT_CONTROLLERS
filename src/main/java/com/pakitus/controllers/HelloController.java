package com.pakitus.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//Clase controlador MVC, maneja las peticiones HTTP
@Controller
public class HelloController {

    @GetMapping("/hola") // http://localhost:8080/hola
    public String saludar() {

        return "hello"; //nombre archivo html (hello.html)
    }

}
