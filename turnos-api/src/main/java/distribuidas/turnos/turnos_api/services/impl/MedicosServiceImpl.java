package distribuidas.turnos.turnos_api.services.impl;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import distribuidas.turnos.turnos_api.model.dto.MedicoDto;
import distribuidas.turnos.turnos_api.model.mapper.MedicoMapper;
import distribuidas.turnos.turnos_api.repository.MedicoRepository;
import distribuidas.turnos.turnos_api.repository.EspecialidadRepository;
import distribuidas.turnos.turnos_api.services.MedicoService;
import distribuidas.turnos.turnos_api.entities.Medico;
import distribuidas.turnos.turnos_api.entities.Especialidad;
 
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicosServiceImpl implements MedicoService {


    private static final Logger logger = LoggerFactory.getLogger(MedicosServiceImpl.class);

    @Autowired
    private EspecialidadRepository especialidadRepository;
    @Autowired
    private MedicoMapper medicoMapper;
    @Autowired
    private MedicoRepository medicoRepository;
    

    @Override
    public MedicoDto getMedico(Integer id) {
        logger.info("Buscando entidad con id: {}", id);
        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Medico not found with id " + id));
        MedicoDto resul = medicoMapper.toDto(medico);

        return resul; // Agrega si quieres retornar el DTO
    }

    @Override
    public MedicoDto saveMedico(MedicoDto medico) {
        logger.info("Guardando entidad: {}", medico);
        Medico entity = new Medico();
        entity.setPersona(medico.getIdPersona());
        entity.setEspecialidad(medico.getIdEspecialidad());
        entity.setHorarioFin(medico.getHorarioFin());
        entity.setHorarioInicio(medico.getHorarioInicio());
 
        medicoRepository.save(entity);

        MedicoDto resul = medicoMapper.toDto(entity);
        return resul;
 
    }

    @Override
    public MedicoDto getMedicoById(Integer id) {
        logger.info("Buscando entidad con id: {}", id);
        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Medico not found with id " + id));

        MedicoDto resul = medicoMapper.toDto(medico);
        return resul; 

    }

    @Override
    public MedicoDto updateMedico(Integer id, MedicoDto medico) {
        logger.info("Actualizando entidad con id: {}", id);
        Medico entity = medicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Medico not found with id " + id));

        entity.setEspecialidad(medico.getIdEspecialidad());
        entity.setHorarioFin(medico.getHorarioFin());
        entity.setHorarioInicio(medico.getHorarioInicio());
        medicoRepository.save(entity);

        MedicoDto resul = medicoMapper.toDto(entity);
        return resul; // Agrega si quieres retornar el DTO
   
    }


    @Override
    public void deleteMedico(Integer id) {
        logger.info("Eliminando entidad con id: {}", id);
        Medico entity = medicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Medico not found with id " + id));
        medicoRepository.delete(entity);
    }


    @Override
    public MedicoDto getMedicoByNombre(Integer nombre) {
        logger.info("Buscando entidad con nombre: {}", nombre);
        Medico medico = medicoRepository.findByPersona(nombre)
                .orElseThrow(() -> new EntityNotFoundException("Medico not found with nombre " + nombre));

        MedicoDto resul = medicoMapper.toDto(medico);
        return resul; // Agrega si quieres retornar el DTO
  
    }


    @Override
    public MedicoDto getMedicoByIdEspecialidad(Integer idEspecialidad) {
        logger.info("Buscando entidad con idEspecialidad: {}", idEspecialidad);
        Especialidad especialidad = especialidadRepository.findById(idEspecialidad)
                .orElseThrow(() -> new EntityNotFoundException("Especialidad not found with id " + idEspecialidad));
        List<Medico> medicos = medicoRepository.findByEspecialidad(especialidad);
        if (medicos == null || medicos.isEmpty()) {
            throw new EntityNotFoundException("Medico not found with idEspecialidad " + idEspecialidad);
        }
        MedicoDto resul = medicoMapper.toDto(medicos.get(0));

        return resul; // Retorna el primer médico encontrado
    }


    @Override
    public List<MedicoDto> getAllMedicos() {
        logger.info("Buscando todas las entidades");
        List<Medico> medicos = medicoRepository.findAll();
        if (medicos.isEmpty()) {
            throw new EntityNotFoundException("No se encontraron medicos");
        }
        List<MedicoDto> medicosDto = medicos.stream()
                .map(medicoMapper::toDto)
                .toList();
        logger.info("Se encontraron {} medicos", medicosDto.size());
        return medicosDto;

    }

    @Override
    public List<MedicoDto> getAllMedicosByEspecialidad(Integer idEspecialidad) {
        logger.info("Buscando todas las entidades con idEspecialidad: {}", idEspecialidad);
        Especialidad especialidad = especialidadRepository.findById(idEspecialidad)
                .orElseThrow(() -> new EntityNotFoundException("Especialidad not found with id " + idEspecialidad));
        List<Medico> medicos = medicoRepository.findByEspecialidad(especialidad);
        if (medicos.isEmpty()) {
            throw new EntityNotFoundException("No se encontraron medicos con idEspecialidad " + idEspecialidad);
        }

        List<MedicoDto> medicosDto = medicos.stream()
                .map(medicoMapper::toDto)
                .toList();

        logger.info("Se encontraron {} medicos con idEspecialidad: {}", medicosDto.size(), idEspecialidad);


        return medicosDto;
    }

    @Override
    public String getNombreMedicoByIdPersona(Integer idPersona) {
        logger.info("Buscando nombre de medico por idPersona: {}", idPersona);
        String nombreMedico = medicoRepository.findNombreByIdPersona(idPersona);                
        logger.info("Nombre del medico encontrado: {}", nombreMedico);
        return nombreMedico;
    }
    
    
}
