package com.example.seven.AmbiGPSBeta.service;

import com.example.seven.AmbiGPSBeta.model.EstadoSolicitud;
import com.example.seven.AmbiGPSBeta.model.Rol;
import com.example.seven.AmbiGPSBeta.model.SolicitudRecoleccion;
import com.example.seven.AmbiGPSBeta.model.Usuario;
import com.example.seven.AmbiGPSBeta.repository.SolicitudRecoleccionRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SolicitudRecoleccionServiceTest {

    private final SolicitudRecoleccionRepository repositorio = mock(SolicitudRecoleccionRepository.class);
    private final UsuarioService usuarios = mock(UsuarioService.class);
    private final SolicitudRecoleccionService servicio = new SolicitudRecoleccionService(repositorio, usuarios);

    @Test
    void crearSolicitudFijaFechaYEstadoPendiente() {
        SolicitudRecoleccion solicitud = new SolicitudRecoleccion();
        solicitud.setEstado(EstadoSolicitud.COMPLETADO);
        when(repositorio.save(solicitud)).thenReturn(solicitud);

        assertSame(solicitud, servicio.crearSolicitud(solicitud));

        assertEquals(EstadoSolicitud.PENDIENTE, solicitud.getEstado());
        assertNotNull(solicitud.getFechaSolicitud());
        verify(repositorio).save(solicitud);
    }

    @Test
    void asignarRecicladorValidoGuardaLaAsignacion() {
        SolicitudRecoleccion solicitud = solicitudExistente();
        Usuario reciclador = usuarioConRol("RECICLADOR");
        when(usuarios.buscarUsuarioPorId(2L)).thenReturn(reciclador);
        when(repositorio.save(solicitud)).thenReturn(solicitud);

        assertSame(solicitud, servicio.asignarReciclador(1L, 2L));

        assertSame(reciclador, solicitud.getReciclador());
        assertEquals(EstadoSolicitud.ASIGNADO, solicitud.getEstado());
        verify(repositorio).save(solicitud);
    }

    @Test
    void asignarUsuarioSinRolRecicladorRechazaLaOperacion() {
        SolicitudRecoleccion solicitud = solicitudExistente();
        when(usuarios.buscarUsuarioPorId(2L)).thenReturn(usuarioConRol("USUARIO"));

        assertThrows(RuntimeException.class, () -> servicio.asignarReciclador(1L, 2L));

        assertNull(solicitud.getReciclador());
        assertEquals(EstadoSolicitud.PENDIENTE, solicitud.getEstado());
        verify(repositorio, never()).save(any());
    }

    @Test
    void completarSolicitudRegistraFechaDeFinalizacion() {
        SolicitudRecoleccion solicitud = solicitudExistente();

        servicio.cambiarEstado(1L, EstadoSolicitud.COMPLETADO);

        assertEquals(EstadoSolicitud.COMPLETADO, solicitud.getEstado());
        assertNotNull(solicitud.getFechaFinalizacion());
        verify(repositorio).save(solicitud);
    }

    @Test
    void cancelarSolicitudNoRegistraFechaDeFinalizacion() {
        SolicitudRecoleccion solicitud = solicitudExistente();

        servicio.cambiarEstado(1L, EstadoSolicitud.CANCELADO);

        assertEquals(EstadoSolicitud.CANCELADO, solicitud.getEstado());
        assertNull(solicitud.getFechaFinalizacion());
        verify(repositorio).save(solicitud);
    }

    @Test
    void buscarSolicitudInexistenteFalla() {
        when(repositorio.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> servicio.buscarPorId(99L));
    }

    private SolicitudRecoleccion solicitudExistente() {
        SolicitudRecoleccion solicitud = new SolicitudRecoleccion();
        when(repositorio.findById(1L)).thenReturn(Optional.of(solicitud));
        return solicitud;
    }

    private Usuario usuarioConRol(String nombreRol) {
        Rol rol = new Rol();
        rol.setNombre(nombreRol);
        Usuario usuario = new Usuario();
        usuario.setRol(rol);
        return usuario;
    }
}
