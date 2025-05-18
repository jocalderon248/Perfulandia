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

import com.example.PerfulandiaInventario.model.Producto;
import com.example.PerfulandiaInventario.service.ProductoService;



@RestController
@RequestMapping("/api/v1/inventario/productos")
public class ProductoController {
    @Autowired
    private ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<Producto>> listar(){

        List<Producto> productos = productoService.findAll();
        if(productos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(productos);
    }

    @PostMapping
    public ResponseEntity<Producto> guardar(@RequestBody Producto producto) {
        Producto nuevoProducto = productoService.save(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProducto);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Producto> buscar(@PathVariable Integer id) {
        try {
            Producto producto = productoService.findById(id);
            return ResponseEntity.ok(producto);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizar(@PathVariable Integer id, @RequestBody Producto producto) {
        try {
            Producto prod=productoService.findById(id);
            prod.setId(id);
            prod.setCategoria(producto.getCategoria());
            prod.setCodigo(producto.getCodigo());
            prod.setDescripcion(producto.getDescripcion());
            prod.setNombre(producto.getNombre());
            prod.setPrecioCompra(producto.getPrecioCompra());
            prod.setPrecioVenta(producto.getPrecioVenta());
            
            productoService.save(prod);
            return ResponseEntity.ok(producto);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        try {
            productoService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }


}
