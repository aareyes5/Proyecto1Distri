package distribuidas.turnos.turnos_api.model.mapper;

import distribuidas.turnos.turnos_api.entities.Turno;
import distribuidas.turnos.turnos_api.model.dto.TurnoDto;
 
import org.springframework.stereotype.Component;

@Component
public class TurnoMapper {

    public TurnoDto toDto(Turno entity) {
        if (entity == null) return null;
        return new TurnoDto(
                entity.getIdTurno(),
                entity.getEspecialidad(),
                entity.getMedico(),
                entity.getPaciente(), // Asumiendo que paciente es Integer
                entity.getFecha(),
                entity.getHora()
        );
    }

    public Turno toEntity(TurnoDto dto) {
        if (dto == null) return null;

        Turno turno = new Turno();
        turno.setIdTurno(dto.getIdTurno());
        turno.setEspecialidad(dto.getIdEspecialidad());
        turno.setMedico(dto.getIdMedico());
        turno.setPaciente(dto.getIdPaciente()); // Asumiendo Integer
        turno.setFecha(dto.getFecha());
        turno.setHora(dto.getHora());

        return turno;
    }
}
