package com.example.seven.AmbiGPSBeta.controller;

import com.example.seven.AmbiGPSBeta.model.Reciclaje;
import com.example.seven.AmbiGPSBeta.service.ReciclajeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reciclajes")
public class ReciclajeController {

    private final ReciclajeService reciclajeService;

    public ReciclajeController(
            ReciclajeService reciclajeService) {

        this.reciclajeService = reciclajeService;
    }

    @PostMapping
    public ResponseEntity<Reciclaje> crear(
            @Valid @RequestBody Reciclaje reciclaje) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(reciclajeService.crearReciclaje(reciclaje));
    }

    @GetMapping
    public ResponseEntity<List<Reciclaje>> listar() {

        return ResponseEntity.ok(
                reciclajeService.listarReciclajes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reciclaje> buscar(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                reciclajeService.buscarPorId(id));
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<Reciclaje>> buscarPorTipo(
            @PathVariable String tipo) {

        return ResponseEntity.ok(
                reciclajeService.buscarPorTipo(tipo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reciclaje> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Reciclaje reciclaje) {

        return ResponseEntity.ok(
                reciclajeService.actualizarReciclaje(
                        id, reciclaje));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Long id) {

        reciclajeService.eliminarReciclaje(id);

        return ResponseEntity.noContent().build();
    }
}