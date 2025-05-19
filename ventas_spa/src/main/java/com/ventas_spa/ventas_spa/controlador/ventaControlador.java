package com.ventas_spa.ventas_spa.controlador;

import com.ventas_spa.ventas_spa.model.venta;
import com.ventas_spa.ventas_spa.service.ventaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/venta")
public class ventaControlador {
    
    @Autowired
    private ventaService ventaService;

    @GetMapping
    public ResponseEntity<List<venta>> listar(){
        List<venta> ventas = ventaService.findAll();
        if (ventas.isEmpty()) {
            return ResponseEntity.noContent().build()
        }
        return ResponseEntity.ok(ventas);
    }

    @PostMapping
    public ResponseEntity<venta> guardar(@RequestBody venta venta){
        venta nuevaVenta = ventaService.save(venta);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaVenta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<venta> buscar(@PathVariable Integer id){
        try{
            venta venta = ventaService.findByID(id);
            return ResponseEntity.ok(venta);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<venta> actualizar(@PathVariable Integer id, @RequestBody venta venta){
        try{
            venta vent=ventaService.findByID(id);
            vent.setId(id);
            vent.setCodigo(venta.getCodigo());
            vent.setTotal(venta.getTotal());
            vent.setFecha(venta.getFecha());
            vent.setPagoConfirmado(venta.getPagoConfirmado());

            ventaService.save(vent);
            return ResponseEntity.ok(venta);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        try{
            ventaService.delete(id);
            return ResponseEntity.noContent().build();
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
