package com.example.seven.AmbiGPSBeta.controller;

import com.example.seven.AmbiGPSBeta.model.TipoMaterial;
import com.example.seven.AmbiGPSBeta.service.TipoMaterialService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipos-material")
public class TipoMaterialController {

    private final TipoMaterialService tipoMaterialService;

    public TipoMaterialController(TipoMaterialService tipoMaterialService) {
        this.tipoMaterialService = tipoMaterialService;
    }

    @PostMapping
    public ResponseEntity<TipoMaterial> crear(@Valid @RequestBody TipoMaterial tipoMaterial) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(tipoMaterialService.crearTipoMaterial(tipoMaterial));
    }

    @GetMapping
    public ResponseEntity<List<TipoMaterial>> listar() {
        return ResponseEntity.ok(tipoMaterialService.listarTiposMaterial());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoMaterial> buscar(@PathVariable Integer id) {
        return ResponseEntity.ok(tipoMaterialService.buscarPorId(id));
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<TipoMaterial> buscarPorNombre(@PathVariable String nombre) {
        return ResponseEntity.ok(tipoMaterialService.buscarPorNombre(nombre));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoMaterial> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody TipoMaterial tipoMaterial) {
        return ResponseEntity.ok(tipoMaterialService.actualizarTipoMaterial(id, tipoMaterial));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        tipoMaterialService.eliminarTipoMaterial(id);
        return ResponseEntity.noContent().build();
    }
}
