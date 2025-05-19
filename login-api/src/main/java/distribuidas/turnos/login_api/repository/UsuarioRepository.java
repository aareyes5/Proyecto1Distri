package distribuidas.turnos.login_api.repository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import distribuidas.turnos.login_api.entities.Persona;
import distribuidas.turnos.login_api.entities.Usuarios;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuarios, Integer> {
 
    Usuarios findByUsuario(String usuario);

    Usuarios findByIdPersona(Persona idPersona);
    
    
}
