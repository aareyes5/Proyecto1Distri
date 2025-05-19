package distribuidas.turnos.turnos_api.model.mapper;

import distribuidas.turnos.turnos_api.entities.Medico;
import distribuidas.turnos.turnos_api.model.dto.MedicoDto;
import org.springframework.stereotype.Component;

@Component
public class MedicoMapper {

    public MedicoDto toDto(Medico entity) {
        if (entity == null) return null;
        return new MedicoDto(
                entity.getIdMedico(),
                entity.getPersona(), // Asumiendo que persona es Integer
                entity.getEspecialidad(),
                entity.getHorarioInicio(),
                entity.getHorarioFin()
        );
    }

    public  Medico toEntity(MedicoDto dto) {
        if (dto == null) return null;

        Medico medico = new Medico();
        medico.setIdMedico(dto.getIdMedico());
        medico.setPersona(dto.getIdPersona()); // Asumiendo Integer
        medico.setEspecialidad(dto.getIdEspecialidad());
        medico.setHorarioInicio(dto.getHorarioInicio());
        medico.setHorarioFin(dto.getHorarioFin());

        return medico;
    }
}
