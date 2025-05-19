package distribuidas.turnos.login_api.controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import distribuidas.turnos.login_api.service.UsuariosService;
import distribuidas.turnos.login_api.model.dto.UsuarioDto;
import distribuidas.turnos.login_api.model.dto.ApiResponseDto;
import distribuidas.turnos.login_api.model.dto.LoginRequestDto;
import distribuidas.turnos.login_api.model.dto.LoginResponseDto;
import distribuidas.turnos.login_api.security.JwtUtil;
import distribuidas.turnos.login_api.model.dto.RegisterDto;

import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("api/auth/Usuarios")
public class UsuarioController {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    private UsuariosService usuariosService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<UsuarioDto>> getUsuarioById(@PathVariable("id") Integer id) {
        logger.info("Buscando usuario con id: {}", id);
        try {
            UsuarioDto dto = usuariosService.getUsuario(id);
            ApiResponseDto<UsuarioDto> response = new ApiResponseDto<>("1", "Exito", dto);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error al buscar usuario con id: {}", id, e);
            ApiResponseDto<UsuarioDto> errorResponse = new ApiResponseDto<>("0", "Error al buscar usuario", null);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponseDto<List<UsuarioDto>>> getAllUsuarios() {
        logger.info("Buscando todos los usuarios");
        try {
            List<UsuarioDto> usuarios = usuariosService.getAllUsuario();
            ApiResponseDto<List<UsuarioDto>> response = new ApiResponseDto<>("1", "Exito", usuarios);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error al buscar todos los usuarios", e);
            ApiResponseDto<List<UsuarioDto>> errorResponse = new ApiResponseDto<>("0", "Error al buscar usuarios", null);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponseDto<UsuarioDto>> saveUsuario(@RequestBody UsuarioDto usuario) {
        logger.info("Guardando usuario: {}", usuario);
        try {
            UsuarioDto savedUsuario = usuariosService.saveUsuario(usuario);
            ApiResponseDto<UsuarioDto> response = new ApiResponseDto<>("1", "Usuario guardado exitosamente", savedUsuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            logger.error("Error al guardar usuario", e);
            ApiResponseDto<UsuarioDto> errorResponse = new ApiResponseDto<>("0", "Error al guardar usuario", null);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDto<UsuarioDto>> updateUsuario(@PathVariable("id") Integer id, @RequestBody UsuarioDto usuario) {
        logger.info("Actualizando usuario con id: {}", id);
        try {
            UsuarioDto updatedUsuario = usuariosService.updateUsuario(id, usuario);
            ApiResponseDto<UsuarioDto> response = new ApiResponseDto<>("1", "Usuario actualizado exitosamente", updatedUsuario);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error al actualizar usuario con id: {}", id, e);
            ApiResponseDto<UsuarioDto> errorResponse = new ApiResponseDto<>("0", "Error al actualizar usuario", null);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto<Void>> deleteUsuario(@PathVariable("id") Integer id) {
        logger.info("Eliminando usuario con id: {}", id);
        try {
            usuariosService.deleteUsuario(id);
            ApiResponseDto<Void> response = new ApiResponseDto<>("1", "Usuario eliminado exitosamente", null);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error al eliminar usuario con id: {}", id, e);
            ApiResponseDto<Void> errorResponse = new ApiResponseDto<>("0", "Error al eliminar usuario", null);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/login")
    public ResponseEntity<ApiResponseDto<LoginResponseDto>> login(@RequestBody LoginRequestDto loginDto) {
        logger.info("Intento de login para usuario: {}", loginDto.getUsuario());
        try {
            UsuarioDto usuario = usuariosService.login(loginDto.getUsuario(), loginDto.getContrasena());
            String token = jwtUtil.generateToken(usuario.getUsuario());
            
            LoginResponseDto responseDto = new LoginResponseDto("Login exitoso", usuario, token);
            ApiResponseDto<LoginResponseDto> response = new ApiResponseDto<>("1", "Éxito", responseDto);
            return ResponseEntity.ok(response);
        } catch (EntityNotFoundException | IllegalArgumentException e) {
            logger.error("Error en login: {}", e.getMessage());
            ApiResponseDto<LoginResponseDto> errorResponse = new ApiResponseDto<>("0", "Credenciales inválidas", null);
            return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            logger.error("Error inesperado en login", e);
            ApiResponseDto<LoginResponseDto> errorResponse = new ApiResponseDto<>("0", "Error interno", null);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponseDto<RegisterDto>> register(@RequestBody RegisterDto registrer) {
        logger.info("Registro de nuevo usuario: {}", registrer);
        try {
            RegisterDto savedUsuario = usuariosService.register(registrer);
            ApiResponseDto<RegisterDto> response = new ApiResponseDto<>("1", "Usuario registrado exitosamente", savedUsuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            logger.error("Error al registrar usuario", e);
            ApiResponseDto<RegisterDto> errorResponse = new ApiResponseDto<>("0", "Error al registrar usuario", null);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    
}
