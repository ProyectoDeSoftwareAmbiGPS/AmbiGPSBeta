package com.example.seven.AmbiGPSBeta.config;

import com.example.seven.AmbiGPSBeta.model.*;
import com.example.seven.AmbiGPSBeta.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.logging.Logger;

@Configuration
public class DataLoaderConfig {

    private static final Logger logger = Logger.getLogger(DataLoaderConfig.class.getName());

    @Bean
    CommandLineRunner initDatabase(
            RolRepository rolRepository,
            TipoMaterialRepository tipoMaterialRepository,
            DepartamentoRepository departamentoRepository,
            MunicipioRepository municipioRepository,
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder) {

        return args -> {
            // Verificar si ya existen datos
            if (rolRepository.count() > 0) {
                logger.info("Data ya existe en la base de datos, saltando inicialización");
                return;
            }

            logger.info("========== INICIALIZANDO BASE DE DATOS ==========");

            // 1. Insertar Roles
            logger.info("Insertando Roles...");
            Rol adminRol = new Rol("ADMIN", "Administrador del sistema");
            Rol recicladorRol = new Rol("RECICLADOR", "Personal encargado de recolectar materiales");
            Rol usuarioRol = new Rol("USUARIO", "Generador de residuos/reciclaje");

            rolRepository.save(adminRol);
            rolRepository.save(recicladorRol);
            rolRepository.save(usuarioRol);
            logger.info("✓ 3 roles creados");

            // 2. Insertar Tipos de Material
            logger.info("Insertando Tipos de Material...");
            TipoMaterial plastico = new TipoMaterial("Plástico", "Botellas PET, tapas, envases limpios");
            TipoMaterial papel = new TipoMaterial("Papel y Cartón", "Cajas, periódicos, archivos secos");
            TipoMaterial vidrio = new TipoMaterial("Vidrio", "Botellas y frascos sin romper");
            TipoMaterial metal = new TipoMaterial("Metal", "Latas de aluminio y chatarra");

            tipoMaterialRepository.save(plastico);
            tipoMaterialRepository.save(papel);
            tipoMaterialRepository.save(vidrio);
            tipoMaterialRepository.save(metal);
            logger.info("✓ 4 tipos de material creados");

            // 3. Insertar Departamentos
            logger.info("Insertando Departamentos...");
            Departamento sansalvadorDep = new Departamento("San Salvador");
            Departamento cuscatlanDep = new Departamento("Cuscatlán");
            Departamento ahuachapanDep = new Departamento("Ahuachapán");
            Departamento sonsonateDep = new Departamento("Sonsonate");

            departamentoRepository.save(sansalvadorDep);
            departamentoRepository.save(cuscatlanDep);
            departamentoRepository.save(ahuachapanDep);
            departamentoRepository.save(sonsonateDep);
            logger.info("✓ 4 departamentos creados");

            // 4. Insertar Municipios
            logger.info("Insertando Municipios...");
            Municipio sansalvadorMun = new Municipio(sansalvadorDep, "San Salvador");
            Municipio ilopangoMun = new Municipio(sansalvadorDep, "Ilopango");
            Municipio cojutepeque = new Municipio(cuscatlanDep, "Cojutepeque");
            Municipio elcojMun = new Municipio(cuscatlanDep, "El Cojinete");
            Municipio ahuachapanMun = new Municipio(ahuachapanDep, "Ahuachapán");
            Municipio atiquizaya = new Municipio(ahuachapanDep, "Atiquizaya");
            Municipio sonsonateMun = new Municipio(sonsonateDep, "Sonsonate");
            Municipio izaltagMun = new Municipio(sonsonateDep, "Izaltagua");

            municipioRepository.save(sansalvadorMun);
            municipioRepository.save(ilopangoMun);
            municipioRepository.save(cojutepeque);
            municipioRepository.save(elcojMun);
            municipioRepository.save(ahuachapanMun);
            municipioRepository.save(atiquizaya);
            municipioRepository.save(sonsonateMun);
            municipioRepository.save(izaltagMun);
            logger.info("✓ 8 municipios creados");

            // 5. Insertar Usuarios
            logger.info("Insertando Usuarios...");

            // Usuario ADMIN
            Usuario admin = new Usuario();
            admin.setNombreCompleto("Juan Pérez Admin");
            admin.setCorreo("admin@ambigps.com");
            admin.setPasswordHash(passwordEncoder.encode("Admin@123456"));
            admin.setTelefono("23456789");
            admin.setDireccion("Calle Principal, San Salvador");
            admin.setRol(adminRol);
            admin.setMunicipio(sansalvadorMun);
            admin.setEstado(EstadoUsuario.ACTIVO);
            usuarioRepository.save(admin);

            // Usuario RECICLADOR
            Usuario reciclador1 = new Usuario();
            reciclador1.setNombreCompleto("Carlos Rodríguez Reciclador");
            reciclador1.setCorreo("reciclador1@ambigps.com");
            reciclador1.setPasswordHash(passwordEncoder.encode("Recicl@123456"));
            reciclador1.setTelefono("71234567");
            reciclador1.setDireccion("Av. Recolector, Ilopango");
            reciclador1.setRol(recicladorRol);
            reciclador1.setMunicipio(ilopangoMun);
            reciclador1.setEstado(EstadoUsuario.ACTIVO);
            usuarioRepository.save(reciclador1);

            Usuario reciclador2 = new Usuario();
            reciclador2.setNombreCompleto("María García Recicladora");
            reciclador2.setCorreo("reciclador2@ambigps.com");
            reciclador2.setPasswordHash(passwordEncoder.encode("Recicl@123456"));
            reciclador2.setTelefono("71234568");
            reciclador2.setDireccion("Calle Reciclaje, Cojutepeque");
            reciclador2.setRol(recicladorRol);
            reciclador2.setMunicipio(cojutepeque);
            reciclador2.setEstado(EstadoUsuario.ACTIVO);
            usuarioRepository.save(reciclador2);

            // Usuario USUARIO (Cliente Normal)
            Usuario usuario1 = new Usuario();
            usuario1.setNombreCompleto("Luis López Usuario");
            usuario1.setCorreo("usuario1@ambigps.com");
            usuario1.setPasswordHash(passwordEncoder.encode("Usuar@123456"));
            usuario1.setTelefono("60123456");
            usuario1.setDireccion("Residencial Los Pinos, San Salvador");
            usuario1.setRol(usuarioRol);
            usuario1.setMunicipio(sansalvadorMun);
            usuario1.setEstado(EstadoUsuario.ACTIVO);
            usuarioRepository.save(usuario1);

            Usuario usuario2 = new Usuario();
            usuario2.setNombreCompleto("Ana Martínez Usuario");
            usuario2.setCorreo("usuario2@ambigps.com");
            usuario2.setPasswordHash(passwordEncoder.encode("Usuar@123456"));
            usuario2.setTelefono("60123457");
            usuario2.setDireccion("Condominio Verde, Ilopango");
            usuario2.setRol(usuarioRol);
            usuario2.setMunicipio(ilopangoMun);
            usuario2.setEstado(EstadoUsuario.ACTIVO);
            usuarioRepository.save(usuario2);

            logger.info("✓ 5 usuarios creados");
            logger.info("========== INICIALIZACIÓN COMPLETADA ==========");
            logger.info("✓ Base de datos poblada exitosamente");
            logger.info("Credenciales de prueba:");
            logger.info("  ADMIN: admin@ambigps.com / Admin@123456");
            logger.info("  RECICLADOR: reciclador1@ambigps.com / Recicl@123456");
            logger.info("  USUARIO: usuario1@ambigps.com / Usuar@123456");
        };
    }
}
