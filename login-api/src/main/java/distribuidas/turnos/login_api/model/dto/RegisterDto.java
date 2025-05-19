package distribuidas.turnos.login_api.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterDto {

    private String usuario;
    private String mail;
    private String contrasena;
    private Boolean estado;
    private String nombre;
    private Integer edad;
    private String cedula;
    private String rol;
    
}
