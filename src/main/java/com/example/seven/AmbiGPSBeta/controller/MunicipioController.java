package com.example.seven.AmbiGPSBeta.controller;

import com.example.seven.AmbiGPSBeta.model.Municipio;
import com.example.seven.AmbiGPSBeta.service.MunicipioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/municipios")
public class MunicipioController {

    private final MunicipioService municipioService;

    public MunicipioController(MunicipioService municipioService) {
        this.municipioService = municipioService;
    }

    @PostMapping
    public ResponseEntity<Municipio> crear(@Valid @RequestBody Municipio municipio) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(municipioService.crearMunicipio(municipio));
    }

    @GetMapping
    public ResponseEntity<List<Municipio>> listar() {
        return ResponseEntity.ok(municipioService.listarMunicipios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Municipio> buscar(@PathVariable Integer id) {
        return ResponseEntity.ok(municipioService.buscarPorId(id));
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<Municipio> buscarPorNombre(@PathVariable String nombre) {
        return ResponseEntity.ok(municipioService.buscarPorNombre(nombre));
    }

    @GetMapping("/departamento/{idDepartamento}")
    public ResponseEntity<List<Municipio>> buscarPorDepartamento(
            @PathVariable Integer idDepartamento) {
        return ResponseEntity.ok(
                municipioService.buscarPorDepartamento(idDepartamento));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Municipio> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody Municipio municipio) {
        return ResponseEntity.ok(municipioService.actualizarMunicipio(id, municipio));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        municipioService.eliminarMunicipio(id);
        return ResponseEntity.noContent().build();
    }
}
