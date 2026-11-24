package com.example.seven.AmbiGPSBeta.integration;

import com.example.seven.AmbiGPSBeta.model.Rol;
import com.example.seven.AmbiGPSBeta.model.TipoMaterial;
import com.example.seven.AmbiGPSBeta.model.Usuario;
import com.example.seven.AmbiGPSBeta.repository.RolRepository;
import com.example.seven.AmbiGPSBeta.repository.TipoMaterialRepository;
import com.example.seven.AmbiGPSBeta.repository.UsuarioRepository;
import com.example.seven.AmbiGPSBeta.security.JwtService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integracion")
class CatalogoIntegracionTest {

    @LocalServerPort
    private int puerto;

    @Autowired
    private UsuarioRepository usuarios;

    @Autowired
    private RolRepository roles;

    @Autowired
    private TipoMaterialRepository materiales;

    @Autowired
    private PasswordEncoder codificador;

    @Autowired
    private UserDetailsService detallesUsuarios;

    @Autowired
    private JwtService jwtService;

    @Test
    @DisplayName("DarNelli - Consultar catálogo con JWT devuelve el material guardado")
    void consultarCatalogoConTokenDevuelveMaterialGuardado() throws Exception {
        // Preparar datos exclusivos de esta ejecución.
        String identificador = UUID.randomUUID().toString();
        String correo = "darnelli-" + identificador + "@example.test";
        String nombreMaterial = "Vidrio-" + identificador;

        Rol rolUsuario = roles.findByNombre("USUARIO")
                .orElseGet(() -> roles.save(
                        new Rol("USUARIO", "Ciudadano")));

        Usuario cuenta = new Usuario();
        cuenta.setNombreCompleto("DarNelli Prueba");
        cuenta.setCorreo(correo);
        cuenta.setPasswordHash(codificador.encode("ClavePrueba123"));
        cuenta.setRol(rolUsuario);

        TipoMaterial material = new TipoMaterial(
                nombreMaterial, "Material de la prueba de integración");

        try {
            // Guardar usando repositorios y MySQL reales.
            usuarios.saveAndFlush(cuenta);
            materiales.saveAndFlush(material);

            var usuarioAutenticado =
                    detallesUsuarios.loadUserByUsername(correo);

            String token = jwtService.generarToken(usuarioAutenticado);

            // Consultar la API real en el puerto asignado a esta prueba.
            HttpRequest peticion = HttpRequest.newBuilder()
                    .uri(URI.create(
                            "http://localhost:" + puerto
                                    + "/api/tipos-material"))
                    .timeout(Duration.ofSeconds(15))
                    .header("Authorization", "Bearer " + token)
                    .header("Accept", "application/json")
                    .GET()
                    .build();

            HttpClient cliente = HttpClient.newHttpClient();

            HttpResponse<String> respuesta = cliente.send(
                    peticion, HttpResponse.BodyHandlers.ofString());

            // Verificar la respuesta y el material preparado.
            assertEquals(200, respuesta.statusCode(),
                    "La consulta con JWT válido debe devolver HTTP 200");

            assertTrue(respuesta.headers()
                    .firstValue("Content-Type")
                    .orElse("")
                    .contains("application/json"));

            assertTrue(respuesta.body().contains(nombreMaterial),
                    "El catálogo debe incluir el material guardado en MySQL");

            System.out.println(
                    "INTEGRACION APROBADA - JWT aceptado, HTTP 200 "
                    + "y material de MySQL encontrado en el catalogo.");
        } finally {
            // Retirar únicamente los datos creados por esta prueba.
            if (material.getIdTipoMaterial() != null) {
                materiales.deleteById(material.getIdTipoMaterial());
            }

            if (cuenta.getIdUsuario() != null) {
                usuarios.deleteById(cuenta.getIdUsuario());
            }
        }
    }
}
