package com.example.seven.AmbiGPSBeta.service;

import com.example.seven.AmbiGPSBeta.model.SolicitudRecoleccion;
import com.example.seven.AmbiGPSBeta.model.Usuario;
import com.example.seven.AmbiGPSBeta.model.EstadoSolicitud;
import com.example.seven.AmbiGPSBeta.repository.SolicitudRecoleccionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SolicitudRecoleccionService {

    private final SolicitudRecoleccionRepository solicitudRepository;
    private final UsuarioService usuarioService;

    public SolicitudRecoleccionService(
            SolicitudRecoleccionRepository solicitudRepository,
            UsuarioService usuarioService) {
        this.solicitudRepository = solicitudRepository;
        this.usuarioService = usuarioService;
    }

    public SolicitudRecoleccion crearSolicitud(SolicitudRecoleccion solicitud) {
        solicitud.setFechaSolicitud(LocalDateTime.now());
        solicitud.setEstado(EstadoSolicitud.PENDIENTE);
        return solicitudRepository.save(solicitud);
    }

    public List<SolicitudRecoleccion> listarSolicitudes() {
        return solicitudRepository.findAll();
    }

    public SolicitudRecoleccion buscarPorId(Long id) {
        return solicitudRepository.findById(id)
                .orElseThrow(() -> 
                    new RuntimeException("Solicitud no encontrada con id: " + id));
    }

    public List<SolicitudRecoleccion> buscarPorUsuario(Long idUsuario) {
        return solicitudRepository.findByUsuarioIdUsuario(idUsuario);
    }

    public List<SolicitudRecoleccion> buscarPorReciclador(Long idReciclador) {
        return solicitudRepository.findByRecicladorIdUsuario(idReciclador);
    }

    public List<SolicitudRecoleccion> buscarPorEstado(EstadoSolicitud estado) {
        return solicitudRepository.findByEstado(estado);
    }

    public List<SolicitudRecoleccion> buscarPorTipoMaterial(Integer idTipoMaterial) {
        return solicitudRepository.findByTipoMaterialIdTipoMaterial(idTipoMaterial);
    }

    public SolicitudRecoleccion asignarReciclador(Long idSolicitud, Long idReciclador) {
        SolicitudRecoleccion solicitud = buscarPorId(idSolicitud);
        Usuario reciclador = usuarioService.buscarUsuarioPorId(idReciclador);

        // Validar que el usuario tiene rol RECICLADOR
        if (!reciclador.getRol().getNombre().equals("RECICLADOR")) {
            throw new RuntimeException("El usuario con id " + idReciclador + " no es un reciclador");
        }

        solicitud.setReciclador(reciclador);
        solicitud.setEstado(EstadoSolicitud.ASIGNADO);

        return solicitudRepository.save(solicitud);
    }

    public SolicitudRecoleccion cambiarEstado(Long idSolicitud, EstadoSolicitud nuevoEstado) {
        SolicitudRecoleccion solicitud = buscarPorId(idSolicitud);
        solicitud.setEstado(nuevoEstado);

        // Si se marca como completada, registrar la fecha de finalización
        if (nuevoEstado == EstadoSolicitud.COMPLETADO) {
            solicitud.setFechaFinalizacion(LocalDateTime.now());
        }

        return solicitudRepository.save(solicitud);
    }

    public SolicitudRecoleccion actualizarSolicitud(Long id, SolicitudRecoleccion datos) {
        SolicitudRecoleccion solicitud = buscarPorId(id);
        solicitud.setTipoMaterial(datos.getTipoMaterial());
        solicitud.setFotoReciclaje(datos.getFotoReciclaje());
        // No permitir cambiar usuario, estado y reciclador aquí (usar métodos específicos)
        return solicitudRepository.save(solicitud);
    }

    public void eliminarSolicitud(Long id) {
        SolicitudRecoleccion solicitud = buscarPorId(id);
        solicitudRepository.delete(solicitud);
    }
}
