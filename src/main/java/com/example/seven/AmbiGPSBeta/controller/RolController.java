package com.example.seven.AmbiGPSBeta.controller;

import com.example.seven.AmbiGPSBeta.model.Rol;
import com.example.seven.AmbiGPSBeta.service.RolService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RolController {

    private final RolService rolService;

    public RolController(RolService rolService) {
        this.rolService = rolService;
    }

    @PostMapping
    public ResponseEntity<Rol> crear(@Valid @RequestBody Rol rol) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(rolService.crearRol(rol));
    }

    @GetMapping
    public ResponseEntity<List<Rol>> listar() {
        return ResponseEntity.ok(rolService.listarRoles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rol> buscar(@PathVariable Integer id) {
        return ResponseEntity.ok(rolService.buscarPorId(id));
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<Rol> buscarPorNombre(@PathVariable String nombre) {
        return ResponseEntity.ok(rolService.buscarPorNombre(nombre));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rol> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody Rol rol) {
        return ResponseEntity.ok(rolService.actualizarRol(id, rol));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        rolService.eliminarRol(id);
        return ResponseEntity.noContent().build();
    }
}
