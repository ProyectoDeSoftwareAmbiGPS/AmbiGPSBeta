package com.example.seven.AmbiGPSBeta.controller;

import com.example.seven.AmbiGPSBeta.model.QR;
import com.example.seven.AmbiGPSBeta.service.QRService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/qr")
public class QRController {

    private final QRService qrService;

    public QRController(QRService qrService) {
        this.qrService = qrService;
    }

    @PostMapping
    public ResponseEntity<QR> crear(@Valid @RequestBody QR qr) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(qrService.crearQR(qr));
    }

    @GetMapping
    public ResponseEntity<List<QR>> listar() {
        return ResponseEntity.ok(qrService.listarQRs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<QR> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(qrService.buscarPorId(id));
    }

    @GetMapping("/solicitud/{idSolicitud}")
    public ResponseEntity<QR> buscarPorSolicitud(
            @PathVariable Long idSolicitud) {
        return ResponseEntity.ok(qrService.buscarPorSolicitud(idSolicitud));
    }

    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<QR> buscarPorCodigo(@PathVariable String codigo) {
        return ResponseEntity.ok(qrService.buscarPorCodigo(codigo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<QR> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody QR qr) {
        return ResponseEntity.ok(qrService.actualizarQR(id, qr));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        qrService.eliminarQR(id);
        return ResponseEntity.noContent().build();
    }
}
