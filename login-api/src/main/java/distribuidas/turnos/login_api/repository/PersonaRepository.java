package distribuidas.turnos.login_api.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import distribuidas.turnos.login_api.entities.Persona;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {
 

  
}
