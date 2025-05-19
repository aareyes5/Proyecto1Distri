package distribuidas.turnos.turnos_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
 
import distribuidas.turnos.turnos_api.entities.Especialidad;

public interface EspecialidadRepository extends JpaRepository<Especialidad, Integer> {

    List<Especialidad> findByNombre(String nombre);

    // Puedes agregar más métodos personalizados si es necesario

}
