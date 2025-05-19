package com.ventas_spa.ventas_spa.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "venta")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, length = 10, nullable = false)
    private String codigo;

    @Column(nullable = false)
    private Integer total;

    @Column(nullable = false)
    private Date fecha;

    @Column(nullable = false)
    private Boolean pagoConfirmado;
}
