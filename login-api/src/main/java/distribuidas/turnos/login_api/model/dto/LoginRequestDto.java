package distribuidas.turnos.login_api.model.dto;

public class LoginRequestDto {
    private String usuario;
    private String contrasena;

    public LoginRequestDto() {
    }

    public LoginRequestDto(String usuario, String contrasena) {
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
