package com.example.seven.AmbiGPSBeta.service;

import com.example.seven.AmbiGPSBeta.model.EstadoSolicitud;
import com.example.seven.AmbiGPSBeta.model.SolicitudRecoleccion;
import com.example.seven.AmbiGPSBeta.model.TipoMaterial;
import com.example.seven.AmbiGPSBeta.model.Usuario;
import com.example.seven.AmbiGPSBeta.repository.SolicitudRecoleccionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GestionSolicitudesTest {

    @Test
    @DisplayName("Alejandro - Actualizar material y foto conserva la asignación")
    void actualizarConservaUsuarioEstadoYReciclador() {
        
        SolicitudRecoleccionRepository repositorio =
                mock(SolicitudRecoleccionRepository.class);
        UsuarioService usuarios = mock(UsuarioService.class);
        SolicitudRecoleccionService servicio =
                new SolicitudRecoleccionService(repositorio, usuarios);

        Usuario ciudadano = new Usuario();
        Usuario reciclador = new Usuario();

        SolicitudRecoleccion existente = new SolicitudRecoleccion();
        existente.setUsuario(ciudadano);
        existente.setReciclador(reciclador);
        existente.setEstado(EstadoSolicitud.ASIGNADO);
        existente.setTipoMaterial(new TipoMaterial("Papel", "Papel usado"));
        existente.setFotoReciclaje("foto-anterior.jpg");

        TipoMaterial vidrio = new TipoMaterial("Vidrio", "Botellas");
        SolicitudRecoleccion cambios = new SolicitudRecoleccion();
        cambios.setTipoMaterial(vidrio);
        cambios.setFotoReciclaje("foto-nueva.jpg");

        
        cambios.setUsuario(new Usuario());
        cambios.setReciclador(new Usuario());
        cambios.setEstado(EstadoSolicitud.CANCELADO);

        when(repositorio.findById(10L)).thenReturn(Optional.of(existente));
        when(repositorio.save(existente)).thenReturn(existente);

        
        SolicitudRecoleccion resultado =
                servicio.actualizarSolicitud(10L, cambios);

        
        assertSame(vidrio, resultado.getTipoMaterial());
        assertEquals("foto-nueva.jpg", resultado.getFotoReciclaje());

        
        assertSame(ciudadano, resultado.getUsuario());
        assertSame(reciclador, resultado.getReciclador());
        assertEquals(EstadoSolicitud.ASIGNADO, resultado.getEstado());
        verify(repositorio).save(existente);

        System.out.println(
                "PRUEBA APROBADA - Material y foto actualizados. "
                + "Usuario, reciclador y estado conservados.");
    }

    @Test
    @DisplayName("Alejandro - Buscar solicitudes usa el ID del usuario recibido")
    void buscarPorUsuarioConsultaElIdCorrecto() {
        
        SolicitudRecoleccionRepository repositorio =
                mock(SolicitudRecoleccionRepository.class);
        UsuarioService usuarios = mock(UsuarioService.class);
        SolicitudRecoleccionService servicio =
                new SolicitudRecoleccionService(repositorio, usuarios);

        Usuario ciudadano = new Usuario();
        ciudadano.setIdUsuario(4L);

        SolicitudRecoleccion solicitud = new SolicitudRecoleccion();
        solicitud.setUsuario(ciudadano);
        List<SolicitudRecoleccion> esperadas = List.of(solicitud);

        when(repositorio.findByUsuarioIdUsuario(4L)).thenReturn(esperadas);

        List<SolicitudRecoleccion> resultado = servicio.buscarPorUsuario(4L);

        
        assertEquals(esperadas, resultado);
        verify(repositorio).findByUsuarioIdUsuario(4L);

        System.out.println(
                "PRUEBA APROBADA - Se consultaron las solicitudes "
                + "del usuario 4 y se devolvio la lista esperada.");
    }

    @Test
    @DisplayName("Alejandro - Eliminar una solicitud existente llama al repositorio")
    void eliminarSolicitudExistente() {
       
        SolicitudRecoleccionRepository repositorio =
                mock(SolicitudRecoleccionRepository.class);
        UsuarioService usuarios = mock(UsuarioService.class);
        SolicitudRecoleccionService servicio =
                new SolicitudRecoleccionService(repositorio, usuarios);

        SolicitudRecoleccion solicitud = new SolicitudRecoleccion();
        solicitud.setIdSolicitud(10L);

        when(repositorio.findById(10L)).thenReturn(Optional.of(solicitud));

        
        servicio.eliminarSolicitud(10L);

        
        verify(repositorio).findById(10L);
        verify(repositorio).delete(solicitud);

        System.out.println(
                "PRUEBA APROBADA - Se busco la solicitud 10 "
                + "y se solicito su eliminacion al repositorio.");
    }
}