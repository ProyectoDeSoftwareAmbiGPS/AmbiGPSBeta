package com.example.seven.AmbiGPSBeta.service;

import com.example.seven.AmbiGPSBeta.model.Usuario;
import com.example.seven.AmbiGPSBeta.model.EstadoUsuario;
import com.example.seven.AmbiGPSBeta.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario crearUsuario(Usuario usuario) {

        if (usuarioRepository.findByCorreo(usuario.getCorreo()).isPresent()) {
            throw new RuntimeException(
                    "Ya existe un usuario con ese correo");
        }

        // Codificar la contraseña antes de guardar
        usuario.setPasswordHash(passwordEncoder.encode(usuario.getPasswordHash()));

        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarUsuarioPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Usuario no encontrado con id: " + id));
    }

    public Usuario actualizarUsuario(Long id, Usuario datos) {

        Usuario usuario = buscarUsuarioPorId(id);

        usuario.setNombreCompleto(datos.getNombreCompleto());
        usuario.setCorreo(datos.getCorreo());
        usuario.setTelefono(datos.getTelefono());
        usuario.setFechaNacimiento(datos.getFechaNacimiento());
        usuario.setDireccion(datos.getDireccion());
        usuario.setGpsUbicacion(datos.getGpsUbicacion());
        usuario.setMunicipio(datos.getMunicipio());
        usuario.setRol(datos.getRol());
        usuario.setEstado(datos.getEstado());

        return usuarioRepository.save(usuario);
    }

    public void eliminarUsuario(Long id) {

        Usuario usuario = buscarUsuarioPorId(id);

        usuarioRepository.delete(usuario);
    }

    public List<Usuario> buscarPorRol(Integer idRol) {
        return usuarioRepository.findByRolIdRol(idRol);
    }

    public List<Usuario> buscarPorEstado(EstadoUsuario estado) {
        return usuarioRepository.findByEstado(estado);
    }

    public Usuario buscarPorCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> 
                    new RuntimeException("Usuario no encontrado con correo: " + correo));
    }
}