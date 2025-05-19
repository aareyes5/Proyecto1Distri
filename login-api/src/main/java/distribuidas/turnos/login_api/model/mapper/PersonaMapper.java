package distribuidas.turnos.login_api.model.mapper;

import distribuidas.turnos.login_api.model.dto.PersonaDto;
import distribuidas.turnos.login_api.entities.Persona;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonaMapper {

    public Persona toEntity(PersonaDto dto);

    public PersonaDto toDto(Persona entity); 
}
