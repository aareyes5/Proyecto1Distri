package distribuidas.turnos.turnos_api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import distribuidas.turnos.turnos_api.entities.Especialidad;
import distribuidas.turnos.turnos_api.entities.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Integer> {

    Optional<Medico> findByPersona(Integer persona);

    List<Medico> findByEspecialidad(Especialidad especialidad);

    // Puedes agregar más métodos personalizados si es necesario
    @Query(value = """
            select nom.nombre 
            from log.persona nom where nom.id_persona = :idPersona
            """, nativeQuery = true)
    String findNombreByIdPersona(Integer idPersona);

}
