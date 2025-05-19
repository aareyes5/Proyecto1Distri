package distribuidas.turnos.turnos_api.services;

import java.util.List;

import distribuidas.turnos.turnos_api.model.dto.EspecialidadDto;

public interface EspecialidadService {

    EspecialidadDto getEspecialidad(Integer id);

    EspecialidadDto saveEspecialidad(EspecialidadDto especialidad);

    EspecialidadDto getEspecialidadById(Integer id);

    EspecialidadDto updateEspecialidad(Integer id, EspecialidadDto especialidad);

    void deleteEspecialidad(Integer id);

    EspecialidadDto getEspecialidadByNombre(String nombre);

    List<EspecialidadDto> getAllEspecialidades();


}
