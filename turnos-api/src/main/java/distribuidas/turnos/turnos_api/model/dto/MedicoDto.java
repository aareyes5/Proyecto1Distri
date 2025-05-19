package distribuidas.turnos.turnos_api.model.dto;

import lombok.*;
import distribuidas.turnos.turnos_api.entities.Especialidad;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedicoDto {

    private Integer idMedico;
    private Integer idPersona;
    private Especialidad idEspecialidad;
    private LocalTime horarioInicio;
    private LocalTime horarioFin;
}
