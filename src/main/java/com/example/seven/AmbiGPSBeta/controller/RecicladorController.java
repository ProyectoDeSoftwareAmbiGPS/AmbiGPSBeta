package com.example.seven.AmbiGPSBeta.controller;

import com.example.seven.AmbiGPSBeta.model.EstadoReciclador;
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

    public RecicladorController(RecicladorService recicladorService) {
        this.recicladorService = recicladorService;
    }

    
    @PostMapping
    public ResponseEntity<Reciclador> crearReciclador(
            @Valid @RequestBody Reciclador reciclador) {

        Reciclador nuevoReciclador =
                recicladorService.crearReciclador(reciclador);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(nuevoReciclador);
    }

    
    @GetMapping
    public ResponseEntity<List<Reciclador>> listarRecicladores(
            @RequestParam(required = false) String departamento,
            @RequestParam(required = false) String municipio,
            @RequestParam(required = false) EstadoReciclador estado) {

        if (departamento != null && municipio != null) {

            return ResponseEntity.ok(
                    recicladorService.buscarPorUbicacion(
                            departamento,
                            municipio
                    )
            );
        }

        if (estado != null) {

            return ResponseEntity.ok(
                    recicladorService.buscarPorEstado(estado)
            );
        }

        return ResponseEntity.ok(
                recicladorService.listarRecicladores()
        );
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<Reciclador> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                recicladorService.buscarPorId(id)
        );
    }

   
    @PutMapping("/{id}")
    public ResponseEntity<Reciclador> actualizarReciclador(
            @PathVariable Long id,
            @Valid @RequestBody Reciclador reciclador) {

        return ResponseEntity.ok(
                recicladorService.actualizarReciclador(
                        id,
                        reciclador
                )
        );
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarReciclador(
            @PathVariable Long id) {

        recicladorService.eliminarReciclador(id);

        return ResponseEntity.noContent().build();
    }
}