package distribuidas.turnos.login_api.controller;

import org.springframework.web.bind.annotation.RestController;

import distribuidas.turnos.login_api.service.PersonaServices;
import distribuidas.turnos.login_api.model.dto.ApiResponseDto;
import distribuidas.turnos.login_api.model.dto.PersonaDto;

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
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("api/auth/persona")
public class PersonaController {

    private static final Logger logger = LoggerFactory.getLogger(PersonaController.class);

     @Autowired
    private PersonaServices personaServices;

    @Autowired
    private HttpServletRequest request;


    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<PersonaDto>> getPersonaById(@PathVariable("id") Long id) {
        logger.info("Buscando persona con id: {}", id);
        try {
            PersonaDto dto = personaServices.getPersonaById(id);
            ApiResponseDto<PersonaDto> response = new ApiResponseDto<>("1", "Exito", dto);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error al buscar persona con id: {}", id, e);
            ApiResponseDto<PersonaDto> errorResponse = new ApiResponseDto<>("0", "Error al buscar persona", null);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping
    public ResponseEntity<ApiResponseDto<List<PersonaDto>>> getAllPersonas() {
        logger.info("Obteniendo todas las personas");
        try {
            List<PersonaDto> dtos = personaServices.getAllPersonas();
            ApiResponseDto<List<PersonaDto>> response = new ApiResponseDto<>("1", "Exito", dtos);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error al obtener todas las personas", e);
            ApiResponseDto<List<PersonaDto>> errorResponse = new ApiResponseDto<>("0", "Error al obtener todas las personas", null);
            return ResponseEntity.status(500).body(errorResponse);
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponseDto<PersonaDto>> createPersona(@RequestBody PersonaDto dto) {
        logger.info("Creando persona: {}", dto);
        try {
            PersonaDto createdDto = personaServices.savePersona(dto);
            ApiResponseDto<PersonaDto> response = new ApiResponseDto<>("1", "Exito", createdDto);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error al crear persona", e);
            ApiResponseDto<PersonaDto> errorResponse = new ApiResponseDto<>("0", "Error al crear persona", null);
            return ResponseEntity.status(500).body(errorResponse);
        }
    }

    @PutMapping
    public ResponseEntity<ApiResponseDto<PersonaDto>> updatePersona(@PathVariable("id") Long id,
            @RequestBody PersonaDto dto) {
        logger.info("Actualizando persona con id: {}, datos: {}", id, dto);
        try {
            PersonaDto updatedDto = personaServices.updatePersona(id, dto);
            ApiResponseDto<PersonaDto> response = new ApiResponseDto<>("1", "Exito", updatedDto);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error al actualizar persona con id: {}", id, e);
            ApiResponseDto<PersonaDto> errorResponse = new ApiResponseDto<>("0", "Error al actualizar persona", null);
            return ResponseEntity.status(500).body(errorResponse);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto<Void>> deletePersona(@PathVariable("id") Long id) {
        logger.info("Eliminando persona con id: {}", id);
        try {
            personaServices.deletePersona(id);
            ApiResponseDto<Void> response = new ApiResponseDto<>("1", "Exito", null);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error al eliminar persona con id: {}", id, e);
            ApiResponseDto<Void> errorResponse = new ApiResponseDto<>("0", "Error al eliminar persona", null);
            return ResponseEntity.status(500).body(errorResponse);
        }
    }
    



    
}
