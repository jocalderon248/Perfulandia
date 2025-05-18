package com.example.perfulandia_spa.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Entity
@Table(name="envio")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class envioModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable=false)
    private String direccion;

    @Column(nullable = false)
    private String estado;

    @Column(nullable = true)
    private Date fechaEnvio;

    @Column(nullable=false)
    private Date fechaEntrega;

}
