package com.example.seven.AmbiGPSBeta.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.tags.Tag;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI ambiGpsOpenApi() {
        String esquemaJwt = "bearerAuth";
        return new OpenAPI()
                .info(new Info().title("AmbiGPS - Pruebas de seguridad").version("v1"))
                .components(new Components().addSecuritySchemes(esquemaJwt,
                        new SecurityScheme().type(SecurityScheme.Type.HTTP)
                                .scheme("bearer").bearerFormat("JWT")))
                .addSecurityItem(new SecurityRequirement().addList(esquemaJwt));
    }

    @Bean
    OpenApiCustomizer rutasDeSustentacion() {
        return api -> {
            Paths rutas = api.getPaths();
            var login = rutas.get("/api/auth/login").getPost();
            var materiales = rutas.get("/api/tipos-material").getGet();
            var roles = rutas.get("/api/roles").getGet();

            String nombreGrupo = "Pruebas de la guía";
            List<String> grupo = List.of(nombreGrupo);
            api.setTags(List.of(new Tag().name(nombreGrupo)));
            login.setTags(grupo);
            materiales.setTags(grupo);
            roles.setTags(grupo);
            login.setSecurity(List.of());

            api.setPaths(new Paths()
                    .addPathItem("/api/auth/login", new PathItem().post(login))
                    .addPathItem("/api/tipos-material", new PathItem().get(materiales))
                    .addPathItem("/api/roles", new PathItem().get(roles)));
        };
    }
}
