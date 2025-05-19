package distribuidas.turnos.login_api.model.dto;


import java.io.Serializable;
 
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonaDto implements Serializable{

    private static final long serialVersionUID = 1L;

    private Integer idPersona;
    private String nombre;
    private Integer edad;
    private String cedula;
    private String rol;

    
}
