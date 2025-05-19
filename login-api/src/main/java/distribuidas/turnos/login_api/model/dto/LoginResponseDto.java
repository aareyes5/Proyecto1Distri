package distribuidas.turnos.login_api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDto {
    private String message;
    private UsuarioDto usuario;
    private String token;

    public LoginResponseDto(String message, UsuarioDto usuario) {
        this.message = message;
        this.usuario = usuario;
    }

    // Getters y Setters
}