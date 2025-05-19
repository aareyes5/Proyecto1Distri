package distribuidas.turnos.login_api.model.mapper;

import org.springframework.stereotype.Component;

import distribuidas.turnos.login_api.entities.Usuarios;
import distribuidas.turnos.login_api.model.dto.UsuarioDto;
import distribuidas.turnos.login_api.service.PersonaServices;

@Component
public class UsuarioMapper {

    public UsuarioDto toDto(Usuarios entity) {
        if (entity == null) {
            return null;
        }

        UsuarioDto dto = new UsuarioDto();
        dto.setIdUsuario(entity.getIdUsuario());
        dto.setUsuario(entity.getUsuario());
        dto.setMail(entity.getMail());
        dto.setContrasena(entity.getContrasena());
        dto.setIdPersona(entity.getIdPersona());
        dto.setEstado(entity.getEstado());

        return dto;
    }

    public Usuarios toEntity(UsuarioDto dto, PersonaServices personaServices) {
        if (dto == null) {
            return null;
        }

        Usuarios entity = new Usuarios();
        entity.setIdUsuario(dto.getIdUsuario());
        entity.setUsuario(dto.getUsuario());
        entity.setMail(dto.getMail());
        entity.setContrasena(dto.getContrasena());
        entity.setEstado(dto.getEstado());

        if (dto.getIdPersona() != null) {
            entity.setIdPersona(dto.getIdPersona());
        }

        return entity;
    }
}
