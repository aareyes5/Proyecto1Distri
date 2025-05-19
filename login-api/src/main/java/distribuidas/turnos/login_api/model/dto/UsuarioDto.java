package distribuidas.turnos.login_api.model.dto;

import java.io.Serializable;
 
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import distribuidas.turnos.login_api.entities.Persona; // Add this import, adjust the package if needed
 
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDto implements Serializable{

    private static final long serialVersionUID = 1L;

    private Integer idUsuario;
    private String usuario;
    private String mail;
    private String contrasena;
    private Persona idPersona;
    private Boolean estado;
    
}
