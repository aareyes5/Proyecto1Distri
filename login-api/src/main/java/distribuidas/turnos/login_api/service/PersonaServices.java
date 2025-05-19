package distribuidas.turnos.login_api.service;

import distribuidas.turnos.login_api.model.dto.PersonaDto;
import distribuidas.turnos.login_api.entities.Persona;
import java.util.List;

public interface PersonaServices {
    Persona getPersona(Long id);

    PersonaDto savePersona(PersonaDto persona);

    PersonaDto getPersonaById(Long id);

    List<PersonaDto> getAllPersonas();

    PersonaDto updatePersona(Long id, PersonaDto persona);

    void deletePersona(Long id);

    Persona getPersonaByIdMap(Long idPersona);
    
}  
