package distribuidas.turnos.turnos_api.model.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

import distribuidas.turnos.turnos_api.entities.Especialidad;
import distribuidas.turnos.turnos_api.entities.Medico;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TurnoDto {

    private Integer idTurno;
    private Especialidad idEspecialidad;
    private Medico idMedico;
    private Integer idPaciente;
    private LocalDate fecha;
    private LocalTime hora;
}
