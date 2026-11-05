package com.example.seven.AmbiGPSBeta.config;

import com.example.seven.AmbiGPSBeta.security.JwtAuthenticationFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .formLogin(form -> form.disable())
            .httpBasic(basic -> basic.disable())

            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )

            .authorizeHttpRequests(auth -> auth

                // 1. Rutas públicas: no requieren iniciar sesión
                .requestMatchers(
                    HttpMethod.POST,
                    "/api/auth/login",
                    "/api/auth/registro"
                ).permitAll()

                // 2. USUARIOS: solo el administrador administra usuarios
                .requestMatchers("/api/usuarios/**").hasRole("ADMIN")

                // 3. ROLES: solo el administrador los administra
                .requestMatchers("/api/roles/**").hasRole("ADMIN")

                // 4. SOLICITUDES: creación para usuarios y administradores
                .requestMatchers(HttpMethod.POST, "/api/solicitudes/**")
                    .hasAnyRole("ADMIN", "USUARIO")

                // Lectura de solicitudes: requiere autenticación y rol conocido.
                // La propiedad/asignación debe verificarse en el servicio.
                .requestMatchers(HttpMethod.GET, "/api/solicitudes/**")
                    .hasAnyRole("ADMIN", "USUARIO", "RECICLADOR")

                // Actualización: administrador o reciclador
                .requestMatchers(
                    HttpMethod.PUT, "/api/solicitudes/**"
                ).hasAnyRole("ADMIN", "RECICLADOR")

                .requestMatchers(
                    HttpMethod.PATCH, "/api/solicitudes/**"
                ).hasAnyRole("ADMIN", "RECICLADOR")

                // Eliminación: solo administrador
                .requestMatchers(
                    HttpMethod.DELETE, "/api/solicitudes/**"
                ).hasRole("ADMIN")

                // 5. RECICLAJES: administrador o reciclador
                .requestMatchers("/api/reciclajes/**")
                    .hasAnyRole("ADMIN", "RECICLADOR")

                // 6. Tipos de material: todos pueden consultar;
                // solo ADMIN puede crear, modificar o eliminar.
                .requestMatchers(
                    HttpMethod.GET, "/api/tipos-material/**"
                ).hasAnyRole("ADMIN", "USUARIO", "RECICLADOR")

                .requestMatchers("/api/tipos-material/**")
                    .hasRole("ADMIN")

                // 7. Departamentos y municipios: consulta para los tres roles
                .requestMatchers(
                    HttpMethod.GET,
                    "/api/departamentos/**",
                    "/api/municipios/**"
                ).hasAnyRole("ADMIN", "USUARIO", "RECICLADOR")

                .requestMatchers(
                    "/api/departamentos/**",
                    "/api/municipios/**"
                ).hasRole("ADMIN")

                // 8. Cualquier otra ruta requiere autenticación
                .anyRequest().authenticated()
            )

            .exceptionHandling(ex -> ex
                .authenticationEntryPoint((request, response, exception) ->
                    response.sendError(
                        HttpServletResponse.SC_UNAUTHORIZED,
                        "No autenticado: token ausente o inválido"
                    )
                )
                .accessDeniedHandler((request, response, exception) ->
                    response.sendError(
                        HttpServletResponse.SC_FORBIDDEN,
                        "No autorizado: rol sin permisos"
                    )
                )
            )

            .addFilterBefore(
                jwtAuthenticationFilter,
                UsernamePasswordAuthenticationFilter.class
            );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}