package com.example.perfulandia_spa.controller;
import com.example.perfulandia_spa.model.envioModel;
import com.example.perfulandia_spa.service.envioService;


import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
//import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/envios")
public class envioController {
    @Autowired
    private envioService envioService;
    
    @GetMapping
    public ResponseEntity<List<envioModel>> listar() {
        List<envioModel> envio = envioService.findAll();
        if (envio.isEmpty()){
            return ResponseEntity.noContent().build();        
        }
        return ResponseEntity.ok(envio);
    }
    
    @PostMapping
    public ResponseEntity<envioModel> guardar(@RequestBody envioModel envio) {
        envioModel nuevoEnvio = envioService.Save(envio);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoEnvio);
        
    }

    @GetMapping("/{id}")
    public ResponseEntity<envioModel> buscar(@PathVariable Integer id) {
        try{
            envioModel envio = envioService.findById(id);
            return ResponseEntity.ok(envio);
        } catch (Exception e) {
            return ResponseEntity.notFound().build(); 
        }
        
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<envioModel> actualizar(@PathVariable Integer id, @RequestBody envioModel envio) {
        try{
            envioModel env = envioService.findById(id);
            env.setId(id);
            env.setDireccion(envio.getDireccion());
            env.setEstado(envio.getEstado());
            env.setFechaEnvio(envio.getFechaEnvio());
            env.setFechaEntrega(envio.getFechaEntrega());   

            envioService.Save(env);
            return ResponseEntity.ok(envio); 
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try{
            envioService.delete(id);
            return ResponseEntity.noContent().build();
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
