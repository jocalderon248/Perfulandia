package com.example.PerfulandiaInventario.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="inventario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inventario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable=false)
    private String nombreSucursal;

    @Column
    private Integer idProducto;

    @Column(nullable = false)
    private Integer stockDisponible;

    @Column(nullable = false)
    private Integer stockMinimo;

}
