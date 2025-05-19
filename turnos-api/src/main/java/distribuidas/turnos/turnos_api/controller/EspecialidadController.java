package distribuidas.turnos.turnos_api.controller;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.servlet.http.HttpServletRequest;

import distribuidas.turnos.turnos_api.model.dto.EspecialidadDto;

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


import org.springframework.web.bind.annotation.RestController;

import distribuidas.turnos.turnos_api.services.EspecialidadService;

@RestController
@RequestMapping("api/turnos/especialidad")
public class EspecialidadController {


    @Autowired
    private HttpServletRequest request;


    private static final Logger logger = LoggerFactory.getLogger(EspecialidadController.class);

    @Autowired
    private EspecialidadService especialidadService;

    @GetMapping("/{id}")
    public ResponseEntity<EspecialidadDto> getEspecialidadById(@PathVariable("id") Integer id) {
        logger.info("Buscando especialidad con id: {}", id);
        try {
            EspecialidadDto dto = especialidadService.getEspecialidadById(id);
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            logger.error("Error al buscar especialidad con id: {}", id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<EspecialidadDto>> getAllEspecialidades() {
        logger.info("Buscando todas las especialidades");
        try {
            List<EspecialidadDto> especialidades = especialidadService.getAllEspecialidades();
            return ResponseEntity.ok(especialidades);
        } catch (Exception e) {
            logger.error("Error al buscar todas las especialidades", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<EspecialidadDto> createEspecialidad(@RequestBody EspecialidadDto especialidad) {
        logger.info("Creando nueva especialidad: {}", especialidad);
        try {
            EspecialidadDto createdEspecialidad = especialidadService.saveEspecialidad(especialidad);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdEspecialidad);
        } catch (Exception e) {
            logger.error("Error al crear especialidad", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<EspecialidadDto> updateEspecialidad(@PathVariable("id") Integer id, @RequestBody EspecialidadDto especialidad) {
        logger.info("Actualizando especialidad con id: {}", id);
        try {
            EspecialidadDto updatedEspecialidad = especialidadService.updateEspecialidad(id, especialidad);
            return ResponseEntity.ok(updatedEspecialidad);
        } catch (Exception e) {
            logger.error("Error al actualizar especialidad con id: {}", id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEspecialidad(@PathVariable("id") Integer id) {
        logger.info("Eliminando especialidad con id: {}", id);
        try {
            especialidadService.deleteEspecialidad(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            logger.error("Error al eliminar especialidad con id: {}", id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/nombre")
    public ResponseEntity<EspecialidadDto> getEspecialidadByNombre(@RequestParam("nombre") String nombre) {
        logger.info("Buscando especialidad por nombre: {}", nombre);
        try {
            EspecialidadDto dto = especialidadService.getEspecialidadByNombre(nombre);
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            logger.error("Error al buscar especialidad por nombre: {}", nombre, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
 
   
    


}
