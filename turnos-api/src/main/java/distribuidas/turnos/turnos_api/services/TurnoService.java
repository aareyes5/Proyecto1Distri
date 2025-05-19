package distribuidas.turnos.turnos_api.services;

import distribuidas.turnos.turnos_api.entities.Medico;
import distribuidas.turnos.turnos_api.model.dto.TurnoDto;

import java.time.LocalDate;
import java.util.List;

public interface TurnoService {
    
    TurnoDto getTurno(Integer id);

    TurnoDto saveTurno(TurnoDto turno);

    TurnoDto getTurnoById(Integer id);

    TurnoDto updateTurno(Integer id, TurnoDto turno);

    void deleteTurno(Integer id);

    List<TurnoDto> getAllTurnos();

    List<TurnoDto> getAllTurnosByMedico(Medico idMedico);

    List<TurnoDto> getAllTurnosByPaciente(Integer idPaciente);

    List<TurnoDto> getTurnosByMedicoAndFecha(Medico idMedico, LocalDate  fecha);

    List<TurnoDto> getTurnosByPacienteAndFecha(Integer idPaciente, LocalDate  fecha);

    List<TurnoDto> getTurnosByMedicoAndPaciente(Medico idMedico, Integer idPaciente);

    List<TurnoDto> getTurnosByMedicoAndPacienteAndFecha(Medico idMedico, Integer idPaciente, LocalDate  fecha);

}
