package com.example.perfulandia_spa.controller;
import com.example.perfulandia_spa.model.usuarioModel;
import com.example.perfulandia_spa.service.UsuarioService;

import lombok.Delegate;

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
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;
    
    @GetMapping
    public ResponseEntity<List<usuarioModel>> listar() {
        List<usuarioModel> usuarios = usuarioService.findAll();
        if (usuarios.isEmpty()){
            return ResponseEntity.noContent().build();        
        }
        return ResponseEntity.ok(usuarios);
    }
    
    @PostMapping
    public ResponseEntity<usuarioModel> guardar(@RequestBody usuarioModel usuario) {
        usuarioModel nuevoUsuario = usuarioService.Save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
        
    }

    @GetMapping("/{id}")
    public ResponseEntity<usuarioModel> buscar(@PathVariable Integer id) {
        try{
            usuarioModel usuario = usuarioService.findById(id);
            return ResponseEntity.ok(usuario);
        } catch (Exception e) {
            return ResponseEntity.notFound().build(); 
        }
        
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<usuarioModel> actualizar(@PathVariable Integer id, @RequestBody usuarioModel usuario) {
        try{
            usuarioModel usr = usuarioService.findById(id);
            usr.setId(id);
            usr.setApellido(usuario.getApellido());
            usr.setNombre(usuario.getNombre());
            usr.setCorreo(usuario.getCorreo());
            usr.setRun(usuario.getRun());   
            usr.setFechaNacimiento(usuario.getFechaNacimiento());

            usuarioService.Save(usr);
            return ResponseEntity.ok(usuario); 
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try{
            usuarioService.delete(id);
            return ResponseEntity.noContent().build();
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
