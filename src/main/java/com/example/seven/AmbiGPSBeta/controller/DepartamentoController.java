package com.example.seven.AmbiGPSBeta.controller;

import com.example.seven.AmbiGPSBeta.model.Departamento;
import com.example.seven.AmbiGPSBeta.service.DepartamentoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departamentos")
public class DepartamentoController {

    private final DepartamentoService departamentoService;

    public DepartamentoController(DepartamentoService departamentoService) {
        this.departamentoService = departamentoService;
    }

    @PostMapping
    public ResponseEntity<Departamento> crear(@Valid @RequestBody Departamento departamento) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(departamentoService.crearDepartamento(departamento));
    }

    @GetMapping
    public ResponseEntity<List<Departamento>> listar() {
        return ResponseEntity.ok(departamentoService.listarDepartamentos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Departamento> buscar(@PathVariable Integer id) {
        return ResponseEntity.ok(departamentoService.buscarPorId(id));
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<Departamento> buscarPorNombre(@PathVariable String nombre) {
        return ResponseEntity.ok(departamentoService.buscarPorNombre(nombre));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Departamento> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody Departamento departamento) {
        return ResponseEntity.ok(departamentoService.actualizarDepartamento(id, departamento));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        departamentoService.eliminarDepartamento(id);
        return ResponseEntity.noContent().build();
    }
}
