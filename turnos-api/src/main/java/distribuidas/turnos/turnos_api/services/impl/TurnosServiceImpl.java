package distribuidas.turnos.turnos_api.services.impl;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import distribuidas.turnos.turnos_api.model.dto.TurnoDto;
import distribuidas.turnos.turnos_api.entities.Medico;
import distribuidas.turnos.turnos_api.entities.Turno;
import distribuidas.turnos.turnos_api.model.mapper.TurnoMapper;
import distribuidas.turnos.turnos_api.repository.TurnoRepository;
import distribuidas.turnos.turnos_api.services.TurnoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TurnosServiceImpl implements TurnoService {

    private static final Logger logger = LoggerFactory.getLogger(MedicosServiceImpl.class);

    @Autowired
    private TurnoRepository turnoRepository;

    @Autowired
    private TurnoMapper turnoMapper;


    @Override
    public TurnoDto getTurno(Integer id) {
        
        logger.info("Buscando entidad con id: {}", id);
        Turno turno = turnoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Turno not found with id " + id));
        TurnoDto resul = turnoMapper.toDto(turno);
        return resul; 
    }
 
    @Override
    public TurnoDto saveTurno(TurnoDto turno) {
        logger.info("Guardando entidad: {}", turno);
        Turno entity = new Turno();
        entity.setPaciente(turno.getIdPaciente());
        entity.setMedico(turno.getIdMedico());
        entity.setFecha(turno.getFecha());
        entity.setHora(turno.getHora());
        entity.setEspecialidad(turno.getIdEspecialidad());

        turnoRepository.save(entity);

        TurnoDto resul = turnoMapper.toDto(entity);
        return resul;
 
    }

    @Override
    public TurnoDto getTurnoById(Integer id) {
        logger.info("Buscando entidad con id: {}", id);
        Turno turno = turnoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Turno not found with id " + id));
        TurnoDto resul = turnoMapper.toDto(turno);
        return resul; // Agrega si quieres retornar el DTO
 
    }

    @Override
    public TurnoDto updateTurno(Integer id, TurnoDto turno) {
        logger.info("Actualizando entidad con id: {}", id);
        Turno entity = turnoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Turno not found with id " + id));
        entity.setPaciente(turno.getIdPaciente());
        entity.setMedico(turno.getIdMedico());
        entity.setFecha(turno.getFecha());
        entity.setHora(turno.getHora());
        entity.setEspecialidad(turno.getIdEspecialidad());
        turnoRepository.save(entity);

        TurnoDto resul = turnoMapper.toDto(entity);

        return resul;
   
    }

    @Override
    public void deleteTurno(Integer id) {
        logger.info("Eliminando entidad con id: {}", id);
        Turno entity = turnoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Turno not found with id " + id));
        turnoRepository.delete(entity);
        logger.info("Entidad eliminada con éxito");
    }

    @Override
    public List<TurnoDto> getAllTurnos() {
        logger.info("Buscando todos los turnos");
        List<Turno> turnos = turnoRepository.findAll();
        if (turnos.isEmpty()) {
            throw new EntityNotFoundException("No se encontraron turnos");
        }
        List<TurnoDto> turnosDto = turnos.stream()
                .map(turnoMapper::toDto)
                .toList();
        return turnosDto;
    }

    @Override
    public List<TurnoDto> getAllTurnosByMedico(Medico idMedico) {
        logger.info("Buscando todos los turnos por medico con id: {}", idMedico);
        List<Turno> turnos = turnoRepository.findByMedico(idMedico);
        if (turnos.isEmpty()) {
            throw new EntityNotFoundException("No se encontraron turnos para el medico con id " + idMedico);
        }
        List<TurnoDto> turnosDto = turnos.stream()
                .map(turnoMapper::toDto)
                .toList();
        return turnosDto;
    }

    @Override
    public List<TurnoDto> getAllTurnosByPaciente(Integer idPaciente) {
        logger.info("Buscando todos los turnos por paciente con id: {}", idPaciente);
        List<Turno> turnos = turnoRepository.findByPaciente(idPaciente);
        if (turnos.isEmpty()) {
            throw new EntityNotFoundException("No se encontraron turnos para el paciente con id " + idPaciente);
        }
        List<TurnoDto> turnosDto = turnos.stream()
                .map(turnoMapper::toDto)
                .toList();
        return turnosDto;
    }

    @Override
    public List<TurnoDto> getTurnosByMedicoAndFecha(Medico idMedico, LocalDate fecha) {
        logger.info("Buscando turnos por medico con id: {} y fecha: {}", idMedico, fecha);
        List<Turno> turnos = turnoRepository.findByMedicoAndFecha(idMedico, fecha);
        if (turnos.isEmpty()) {
            throw new EntityNotFoundException("No se encontraron turnos para el medico con id " + idMedico + " y fecha " + fecha);
        }
        List<TurnoDto> turnosDto = turnos.stream()
                .map(turnoMapper::toDto)
                .toList();
        return turnosDto;
    }

    @Override
    public List<TurnoDto> getTurnosByPacienteAndFecha(Integer idPaciente, LocalDate  fecha) {
        logger.info("Buscando turnos por paciente con id: {} y fecha: {}", idPaciente, fecha);
        List<Turno> turnos = turnoRepository.findByPacienteAndFecha(idPaciente, fecha);
        if (turnos.isEmpty()) {
            throw new EntityNotFoundException("No se encontraron turnos para el paciente con id " + idPaciente + " y fecha " + fecha);
        }
        List<TurnoDto> turnosDto = turnos.stream()
                .map(turnoMapper::toDto)
                .toList();
        return turnosDto;
    }

    @Override
    public List<TurnoDto> getTurnosByMedicoAndPaciente(Medico idMedico, Integer idPaciente) {
        logger.info("Buscando turnos por medico con id: {} y paciente con id: {}", idMedico, idPaciente);
        List<Turno> turnos = turnoRepository.findByMedicoAndPaciente(idMedico, idPaciente);
        if (turnos.isEmpty()) {
            throw new EntityNotFoundException("No se encontraron turnos para el medico con id " + idMedico + " y paciente con id " + idPaciente);
        }
        List<TurnoDto> turnosDto = turnos.stream()
                .map(turnoMapper::toDto)
                .toList();
        return turnosDto;
    }

    @Override
    public List<TurnoDto> getTurnosByMedicoAndPacienteAndFecha(Medico idMedico, Integer idPaciente, LocalDate  fecha) {
        logger.info("Buscando turnos por medico con id: {}, paciente con id: {} y fecha: {}", idMedico, idPaciente, fecha);
        List<Turno> turnos = turnoRepository.findByMedicoAndPacienteAndFecha(idMedico, idPaciente, fecha);
        if (turnos.isEmpty()) {
            throw new EntityNotFoundException("No se encontraron turnos para el medico con id " + idMedico + ", paciente con id " + idPaciente + " y fecha " + fecha);
        }
        List<TurnoDto> turnosDto = turnos.stream()
                .map(turnoMapper::toDto)
                .toList();
        return turnosDto;
    }
 
}
