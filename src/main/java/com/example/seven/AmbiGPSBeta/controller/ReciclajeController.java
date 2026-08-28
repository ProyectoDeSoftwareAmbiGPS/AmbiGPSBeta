package com.example.seven.AmbiGPSBeta.controller;

import com.example.seven.AmbiGPSBeta.model.Reciclaje;
import com.example.seven.AmbiGPSBeta.model.TipoMaterial;
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

    public ReciclajeController(ReciclajeService reciclajeService) {
        this.reciclajeService = reciclajeService;
    }

    @PostMapping
    public ResponseEntity<Reciclaje> crearReciclaje(
            @Valid @RequestBody Reciclaje reciclaje) {

        Reciclaje nuevoReciclaje =
                reciclajeService.crearReciclaje(reciclaje);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(nuevoReciclaje);
    }

    @GetMapping
    public ResponseEntity<List<Reciclaje>> listarReciclajes(
            @RequestParam(required = false) TipoMaterial tipo) {

        if (tipo != null) {
            return ResponseEntity.ok(
                    reciclajeService.buscarPorTipo(tipo)
            );
        }

        return ResponseEntity.ok(
                reciclajeService.listarReciclajes()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reciclaje> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                reciclajeService.buscarPorId(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reciclaje> actualizarReciclaje(
            @PathVariable Long id,
            @Valid @RequestBody Reciclaje reciclaje) {

        return ResponseEntity.ok(
                reciclajeService.actualizarReciclaje(id, reciclaje)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarReciclaje(
            @PathVariable Long id) {

        reciclajeService.eliminarReciclaje(id);

        return ResponseEntity.noContent().build();
    }
}