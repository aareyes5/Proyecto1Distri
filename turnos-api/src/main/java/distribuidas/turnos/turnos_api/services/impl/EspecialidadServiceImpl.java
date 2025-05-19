package distribuidas.turnos.turnos_api.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import distribuidas.turnos.turnos_api.services.EspecialidadService;
import distribuidas.turnos.turnos_api.model.dto.EspecialidadDto;
import distribuidas.turnos.turnos_api.model.mapper.EspecialidadMapper;
import distribuidas.turnos.turnos_api.repository.EspecialidadRepository;
import distribuidas.turnos.turnos_api.entities.Especialidad;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class EspecialidadServiceImpl implements EspecialidadService{
    private static final Logger logger = LoggerFactory.getLogger(EspecialidadServiceImpl.class);

    @Autowired
    private EspecialidadRepository especialidadRepository;
    
    @Autowired
    private EspecialidadMapper especialidadMapper;

    @Override
    public EspecialidadDto getEspecialidad(Integer id) {
        logger.info("Buscando entidad con id: {}", id);
        Especialidad especialidad = especialidadRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Especialidad not found with id " + id));
        EspecialidadDto resul = especialidadMapper.toDto(especialidad);
        return resul; // Agrega si quieres retornar el DTO
    }

    @Override
    public EspecialidadDto saveEspecialidad(EspecialidadDto especialidad) {
        logger.info("Guardando entidad: {}", especialidad);
        Especialidad entity = new Especialidad();
        entity.setNombre(especialidad.getNombre());
        entity.setPrecio(especialidad.getPrecio());
        entity.setEstado(true);
        entity.setDias(especialidad.getDias());
        especialidadRepository.save(entity);

        EspecialidadDto resul = especialidadMapper.toDto(entity);

        return resul;
    }

    @Override
    public EspecialidadDto getEspecialidadById(Integer id)
    {
        logger.info("Buscando entidad con id: {}", id);
        Especialidad especialidad = especialidadRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Especialidad not found with id " + id));
        EspecialidadDto resul = especialidadMapper.toDto(especialidad);        
        return resul; // Agrega si quieres retornar el DTO

    }

    @Override
    public EspecialidadDto updateEspecialidad(Integer id, EspecialidadDto especialidad) {
        logger.info("Actualizando entidad con id: {}", id);
        Especialidad entity = especialidadRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Especialidad not found with id " + id));

        entity.setNombre(especialidad.getNombre());
        entity.setPrecio(especialidad.getPrecio());
        entity.setEstado(especialidad.getEstado());
        entity.setDias(especialidad.getDias());
        especialidadRepository.save(entity);
        EspecialidadDto resul = especialidadMapper.toDto(entity);

        return resul;
   
    }

    @Override
    public void deleteEspecialidad(Integer id) {
        logger.info("Eliminando entidad con id: {}", id);
        Especialidad entity = especialidadRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Especialidad not found with id " + id));
        especialidadRepository.delete(entity);
        logger.info("Entidad eliminada con id: {}", id);
        
    }

    @Override
    public EspecialidadDto getEspecialidadByNombre(String nombre) {
        logger.info("Buscando entidad con nombre: {}", nombre);
        List<Especialidad> especialidades = especialidadRepository.findByNombre(nombre);
        if (!especialidades.isEmpty()) {
            logger.info("Entidad encontrada con nombre: {}", nombre);
            EspecialidadDto especialidadDto = especialidadMapper.toDto(especialidades.get(0));
            return especialidadDto;
        }else{
            logger.info("No se encontró la entidad con nombre: {}", nombre);
            return null;
        }
    }

    @Override
    public List<EspecialidadDto> getAllEspecialidades() {
        
        logger.info("Obteniendo todas las especialidades");
        List<Especialidad> especialidades = especialidadRepository.findAll();
        if (!especialidades.isEmpty()) {
            logger.info("Se encontraron {} especialidades", especialidades.size());
            // transformar a DTO la lista con lo que tengo en mi mapper que solo es el toDto
            List<EspecialidadDto> especialidadesDto = especialidades.stream()
                    .map(especialidadMapper::toDto)
                    .toList();
            return especialidadesDto;
        } else {
            logger.info("No se encontraron especialidades");
            return null;
        }
    }


}
