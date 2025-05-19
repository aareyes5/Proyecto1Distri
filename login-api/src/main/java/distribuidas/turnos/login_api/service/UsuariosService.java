package distribuidas.turnos.login_api.service;

import distribuidas.turnos.login_api.model.dto.RegisterDto;
import distribuidas.turnos.login_api.model.dto.UsuarioDto;
import java.util.List;


public interface UsuariosService {
    UsuarioDto getUsuario(Integer id);
    
    UsuarioDto saveUsuario(UsuarioDto usuario);
    
    UsuarioDto getUsuarioById(Integer id);
    
    List<UsuarioDto> getAllUsuario();
    
    UsuarioDto updateUsuario(Integer id, UsuarioDto usuario);
    
    void deleteUsuario(Integer id);

    UsuarioDto login(String username, String password);
    
    RegisterDto register(RegisterDto usuario);
        
} 
