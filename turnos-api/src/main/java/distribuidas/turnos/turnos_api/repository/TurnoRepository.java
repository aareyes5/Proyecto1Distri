package distribuidas.turnos.turnos_api.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
 
import distribuidas.turnos.turnos_api.entities.Turno;
import distribuidas.turnos.turnos_api.entities.Medico;

public interface TurnoRepository extends JpaRepository<Turno, Integer> {

    List<Turno> findByMedico(Medico idMedico);
    List<Turno> findByPaciente(Integer idPaciente);
    
    List<Turno> findByMedicoAndFecha(Medico idMedico, LocalDate fecha);

    List<Turno> findByPacienteAndFecha(Integer idPaciente, LocalDate fecha);

    List<Turno> findByMedicoAndPaciente(Medico idMedico, Integer idPaciente);

    List<Turno> findByMedicoAndPacienteAndFecha(Medico idMedico, Integer idPaciente, LocalDate fecha);


}
