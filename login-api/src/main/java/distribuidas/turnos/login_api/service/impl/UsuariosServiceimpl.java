package distribuidas.turnos.login_api.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import distribuidas.turnos.login_api.service.UsuariosService;
import distribuidas.turnos.login_api.service.PersonaServices; // Asegúrate de importar esto
import distribuidas.turnos.login_api.model.dto.UsuarioDto;
import distribuidas.turnos.login_api.model.dto.RegisterDto;
import distribuidas.turnos.login_api.repository.UsuarioRepository;
import distribuidas.turnos.login_api.entities.Usuarios;
import distribuidas.turnos.login_api.entities.Persona;
import distribuidas.turnos.login_api.repository.PersonaRepository;
import distribuidas.turnos.login_api.model.mapper.UsuarioMapper;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuariosServiceimpl implements UsuariosService {

    private static final Logger logger = LoggerFactory.getLogger(UsuariosServiceimpl.class);
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Autowired
    private PersonaServices personaServices; // Cambia de MappingContext a PersonaServices

    @Autowired
    private PersonaRepository personaRepository; // Asegúrate de que este repositorio esté inyectado

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UsuarioDto getUsuario(Integer id) {
        logger.info("Buscando entidad con id: {}", id);
        Usuarios usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario not found with id " + id));

        return usuarioMapper.toDto(usuario); // Agrega si quieres retornar el DTO
    }

    @Override
    public UsuarioDto saveUsuario(UsuarioDto usuario) {
        logger.info("Guardando entidad: {}", usuario);
        Usuarios entity = usuarioMapper.toEntity(usuario, personaServices); // <-- Aquí es el cambio clave
        entity.setEstado(true);
        entity.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
        usuarioRepository.save(entity);

        return usuarioMapper.toDto(entity);
    }

    @Override
    public UsuarioDto getUsuarioById(Integer id) {

        logger.info("Buscando entidad con id: {}", id);
        Usuarios usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario not found with id " + id));
        return usuarioMapper.toDto(usuario);
    }

    @Override
    public List<UsuarioDto> getAllUsuario() {
        logger.info("Buscando todas las entidades");
        List<Usuarios> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(usuarioMapper::toDto)
                .toList();
    }

    @Override
    public UsuarioDto updateUsuario(Integer id, UsuarioDto usuario) {
        logger.info("Actualizando entidad con id: {}", id);
        Usuarios entity = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario not found with id " + id));

        entity.setUsuario(usuario.getUsuario());
        if (usuario.getContrasena() != null && !usuario.getContrasena().isBlank()) {
            entity.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
        }
        entity.setMail(usuario.getMail());
        entity.setEstado(usuario.getEstado());

        usuarioRepository.save(entity);

        return usuarioMapper.toDto(entity);
    }

    @Override
    public void deleteUsuario(Integer id) {
        logger.info("Eliminando entidad con id: {}", id);
        Usuarios entity = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario not found with id " + id));

        usuarioRepository.delete(entity);
        logger.info("Entidad eliminada exitosamente");
    }


    @Override
    public UsuarioDto login(String username, String password) {
        Usuarios usuario = usuarioRepository.findByUsuario(username);
        if (usuario == null) {
            throw new EntityNotFoundException("Usuario no encontrado");
        }

        if (!passwordEncoder.matches(password, usuario.getContrasena())) {
            throw new IllegalArgumentException("Contraseña incorrecta");
        }

        return usuarioMapper.toDto(usuario);
    }

    @Override
    public RegisterDto register(RegisterDto usuario) {
        Usuarios entity = new Usuarios();
        entity.setUsuario(usuario.getUsuario());
        entity.setMail(usuario.getMail());
        entity.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
        entity.setEstado(true);
        //aqui se crea la persona 
        Persona persona = new Persona();
        persona.setNombre(usuario.getNombre());
        persona.setEdad(usuario.getEdad());
        persona.setCedula(usuario.getCedula());
        persona.setRol(usuario.getRol());
        Persona savedPersona = personaRepository.save(persona);
        entity.setIdPersona(savedPersona);
        Usuarios usersave = usuarioRepository.save(entity);

        RegisterDto registerDto = new RegisterDto();
        registerDto.setUsuario(usersave.getUsuario());
        registerDto.setMail(usersave.getMail());
        registerDto.setContrasena(usersave.getContrasena());
        registerDto.setEstado(usersave.getEstado());
        registerDto.setNombre(savedPersona.getNombre());
        registerDto.setEdad(savedPersona.getEdad());
        registerDto.setCedula(savedPersona.getCedula());
        registerDto.setRol(savedPersona.getRol());

        return registerDto;
    }

}
