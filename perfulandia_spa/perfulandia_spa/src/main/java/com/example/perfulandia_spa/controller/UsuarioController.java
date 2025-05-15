package com.example.perfulandia_spa.controller;
import com.example.perfulandia_spa.model.usuarioModel;
import com.example.perfulandia_spa.service.UsuarioService;

import lombok.Delegate;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
//import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.ObjectOutputStream.PutField;
import java.security.PublicKey;
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
    public ResponseEntity<usuarioModel> (@RequestBody String entity) {
        //TODO: process POST request
        
        return entity;
    }
    
}
