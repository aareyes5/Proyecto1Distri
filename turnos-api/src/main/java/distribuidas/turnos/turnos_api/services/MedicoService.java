package distribuidas.turnos.turnos_api.services;

import java.util.List;
 
import distribuidas.turnos.turnos_api.model.dto.MedicoDto;

public interface MedicoService {

    MedicoDto getMedico(Integer id);

    MedicoDto saveMedico(MedicoDto medico);

    MedicoDto getMedicoById(Integer id);

    MedicoDto updateMedico(Integer id, MedicoDto medico);

    void deleteMedico(Integer id);
    
    MedicoDto getMedicoByNombre(Integer nombre);

    MedicoDto getMedicoByIdEspecialidad(Integer idEspecialidad);

    List<MedicoDto> getAllMedicos();

    List<MedicoDto> getAllMedicosByEspecialidad(Integer idEspecialidad);
    
    String getNombreMedicoByIdPersona(Integer idPersona);
}  