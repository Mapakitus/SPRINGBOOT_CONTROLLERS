package com.pakitus.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@ToString(exclude = "productos") // evita el bucle infinito al imprimir productos
@NoArgsConstructor // constructor vacío
@AllArgsConstructor // constructor con todos los parámetros
@Builder // permite crear objetos de forma más sencilla
@Entity
@Table(name= "proveedores") // personaliza el nombre de la tabla
public class Proveedor {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    private String email;
    private String telefono;
    private Boolean activo;

    // MUCHOS productos pueden pertenecer a MUCHOS proveedores
    @ManyToMany
    @JoinTable(name = "proveedor_producto")
    @Builder.Default // inicializa la lista vacía por defecto (un arrayList vacío)
    private List<Producto> productos = new ArrayList<>();
}
