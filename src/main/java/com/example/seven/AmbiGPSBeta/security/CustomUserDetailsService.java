package com.example.seven.AmbiGPSBeta.security;

import com.example.seven.AmbiGPSBeta.model.Usuario;
import com.example.seven.AmbiGPSBeta.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public CustomUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String correo)
            throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByCorreo(correo)
                .orElseThrow(() ->
                    new UsernameNotFoundException(
                        "No existe un usuario con ese correo"
                    )
                );

        String nombreRol = usuario.getRol().getNombre();

        return User.withUsername(usuario.getCorreo())
                .password(usuario.getPasswordHash())
                .authorities("ROLE_" + nombreRol)
                .build();
    }
}