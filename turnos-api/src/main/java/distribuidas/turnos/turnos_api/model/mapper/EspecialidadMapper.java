package distribuidas.turnos.turnos_api.model.mapper;

import distribuidas.turnos.turnos_api.entities.Especialidad;
import distribuidas.turnos.turnos_api.model.dto.EspecialidadDto;
import org.springframework.stereotype.Component;

@Component
public class EspecialidadMapper {

    public  EspecialidadDto toDto(Especialidad entity) {
        if (entity == null) return null;
        return new EspecialidadDto(
                entity.getIdEspecialidad(),
                entity.getNombre(),
                entity.getPrecio(),
                entity.getEstado(),
                entity.getDias()
        );
    }

    public  Especialidad toEntity(EspecialidadDto dto) {
        if (dto == null) return null;
        return new Especialidad(
                dto.getIdEspecialidad(),
                dto.getNombre(),
                dto.getPrecio(),
                dto.getEstado(),
                dto.getDias()
        );
    }
}
