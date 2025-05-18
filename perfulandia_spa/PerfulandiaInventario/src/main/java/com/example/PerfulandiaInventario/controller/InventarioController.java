package com.example.PerfulandiaInventario.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.PerfulandiaInventario.model.Inventario;
import com.example.PerfulandiaInventario.service.InventarioService;


@RestController
@RequestMapping("/api/v1/inventario")

public class InventarioController {
    @Autowired
    private InventarioService inventarioService;

    @GetMapping
    public ResponseEntity<List<Inventario>> listar(){
        List<Inventario> inventarios =inventarioService.findAll();
        if(inventarios.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(inventarios);
    }
    
    @PostMapping
    public ResponseEntity<Inventario> guardad(@RequestBody Inventario inventario) {
        Inventario nuevoInventario = inventarioService.save(inventario);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoInventario);
    }
    

    @GetMapping("/{id}")
    public ResponseEntity<Inventario> buscar(@PathVariable Integer id) {
        try {
            Inventario inventario = inventarioService.findById(id);
            return ResponseEntity.ok(inventario);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Inventario> actualizar(@PathVariable Integer id ,@RequestBody Inventario inventario) {
        try {
            Inventario inv=inventarioService.findById(id);
            inv.setId(id);
            inv.setNombreSucursal(inventario.getNombreSucursal());
            inv.setIdProducto(inventario.getIdProducto());
            inv.setStockDisponible(inventario.getStockDisponible());
            inv.setStockMinimo(inventario.getStockMinimo());

            inventarioService.save(inv);
            return ResponseEntity.ok(inventario);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        } 
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        try {
            inventarioService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
}
