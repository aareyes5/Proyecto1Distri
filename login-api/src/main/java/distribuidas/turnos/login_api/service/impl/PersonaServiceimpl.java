package distribuidas.turnos.login_api.service.impl;

import distribuidas.turnos.login_api.entities.Persona;
import distribuidas.turnos.login_api.model.dto.PersonaDto;
import distribuidas.turnos.login_api.repository.PersonaRepository;
import distribuidas.turnos.login_api.service.PersonaServices;
import distribuidas.turnos.login_api.model.mapper.PersonaMapper;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;

@Service
public class PersonaServiceimpl implements PersonaServices {

    private static final Logger logger = LoggerFactory.getLogger(PersonaServiceimpl.class);

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private PersonaMapper personaMapper;

    @Override
    public Persona getPersona(Long id) {
        return personaRepository.findById(id).orElse(null);
    }

    @Override
    public PersonaDto savePersona(PersonaDto persona) {
        // Implementation here
        logger.info("Guardando entidad: {}", persona);
        Persona entity =  personaMapper.toEntity(persona);
        entity.setRol("Paciente");

        

        entity = personaRepository.save(entity);
        PersonaDto result = personaMapper.toDto(entity);

        logger.info("Entidad guardada exitosamente: {}", result);
        
        return result;
    }

    @Override
    public PersonaDto getPersonaById(Long id) {
        
        logger.info("Buscando entidad con id: {}", id);
        Persona persona = personaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Persona not found with id " + id));
        PersonaDto result = personaMapper.toDto(persona);
        logger.info("Entidad encontrada: {}", result);
        return result;
    }

    @Override
    public List<PersonaDto> getAllPersonas() {
        logger.info("Buscando todas las entidades");
        List<Persona> personas = personaRepository.findAll();
        List<PersonaDto> result = personas.stream()
                .map(personaMapper::toDto)
                .collect(Collectors.toList());
        logger.info("Entidades encontradas: {}", result);
        return result;
 
    }

    @Override
    public PersonaDto updatePersona(Long id, PersonaDto persona) {
        
        logger.info("Actualizando entidad con id: {}", id);
        Persona existingPersona = personaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Persona not found with id " + id));
 
        existingPersona.setEdad(persona.getEdad());
        existingPersona.setRol(persona.getRol());
        
        Persona updatedPersona = personaRepository.save(existingPersona);
        PersonaDto result = personaMapper.toDto(updatedPersona);
        logger.info("Entidad actualizada exitosamente: {}", result);
        return result;
 
    }

    @Override
    public void deletePersona(Long id) {
        
        logger.info("Eliminando entidad con id: {}", id);
        Persona persona = personaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Persona not found with id " + id));
        personaRepository.delete(persona);
        logger.info("Entidad eliminada exitosamente");

        
    }

    @Override
    public Persona getPersonaByIdMap(Long id) {
        return personaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Persona not found with id " + id));
    }

    
}
