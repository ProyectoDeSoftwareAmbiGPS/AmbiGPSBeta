package com.example.seven.AmbiGPSBeta.controller;

import com.example.seven.AmbiGPSBeta.model.Reciclador;
import com.example.seven.AmbiGPSBeta.service.RecicladorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recicladores")
public class RecicladorController {

    private final RecicladorService recicladorService;

    public RecicladorController(
            RecicladorService recicladorService) {

        this.recicladorService = recicladorService;
    }

    @PostMapping
    public ResponseEntity<Reciclador> crear(
            @Valid @RequestBody Reciclador reciclador) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(recicladorService.crearReciclador(reciclador));
    }

    @GetMapping
    public ResponseEntity<List<Reciclador>> listar() {

        return ResponseEntity.ok(
                recicladorService.listarRecicladores());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reciclador> buscar(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                recicladorService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reciclador> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Reciclador reciclador) {

        return ResponseEntity.ok(
                recicladorService.actualizarReciclador(
                        id, reciclador));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Long id) {

        recicladorService.eliminarReciclador(id);

        return ResponseEntity.noContent().build();
    }
}