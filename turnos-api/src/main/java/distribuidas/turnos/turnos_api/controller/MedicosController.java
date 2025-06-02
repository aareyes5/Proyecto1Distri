package distribuidas.turnos.turnos_api.controller;


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

import distribuidas.turnos.turnos_api.model.dto.MedicoDto;
import distribuidas.turnos.turnos_api.services.MedicoService;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/turnos/medicos")
public class MedicosController {

    @Autowired
    private HttpServletRequest request;


    @Autowired
    private MedicoService medicoService;

    private static final Logger logger = LoggerFactory.getLogger(EspecialidadController.class);

    @GetMapping("/{id}")
    public ResponseEntity<MedicoDto> getMedicoById(@PathVariable("id") Integer id) {
        logger.info("Buscando medico con id: {}", id);
        try {
            MedicoDto dto = medicoService.getMedicoById(id);
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            logger.error("Error al buscar medico con id: {}", id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<MedicoDto>> getAllMedicos() {
        logger.info("Buscando todos los medicos");
        try {
            List<MedicoDto> dto = medicoService.getAllMedicos();
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            logger.error("Error al buscar todos los medicos", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<MedicoDto> saveMedico(@RequestBody MedicoDto medico) {
        logger.info("Guardando medico: {}", medico);
        try {
            MedicoDto dto = medicoService.saveMedico(medico);
            return ResponseEntity.status(HttpStatus.CREATED).body(dto);
        } catch (Exception e) {
            logger.error("Error al guardar medico", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicoDto> updateMedico(@PathVariable("id") Integer id, @RequestBody MedicoDto medico) {
        logger.info("Actualizando medico con id: {}", id);
        try {
            MedicoDto dto = medicoService.updateMedico(id, medico);
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            logger.error("Error al actualizar medico con id: {}", id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedico(@PathVariable("id") Integer id) {
        logger.info("Eliminando medico con id: {}", id);
        try {
            medicoService.deleteMedico(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            logger.error("Error al eliminar medico con id: {}", id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/especialidad")
    public ResponseEntity<List<MedicoDto>> getMedicosByEspecialidad(@RequestParam("especialidad") Integer especialidad) {
        logger.info("Buscando medicos por especialidad: {}", especialidad);
        try {
            List<MedicoDto> medicos = medicoService.getAllMedicosByEspecialidad(especialidad);
            return ResponseEntity.ok(medicos);
        } catch (Exception e) {
            logger.error("Error al buscar medicos por especialidad: {}", especialidad, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint para buscar el nombre de un medico por su ID persona
    @GetMapping("/nombres")
    public ResponseEntity<String> getNombreMedicoByIdPersona(@RequestParam("id_persona") Integer idPersona) {
        logger.info("Buscando nombre de medico por ID persona: {}", idPersona);
        try {
            String nombreMedico = medicoService.getNombreMedicoByIdPersona(idPersona);
            return ResponseEntity.ok(nombreMedico);
        } catch (Exception e) {
            logger.error("Error al buscar nombre de medico por ID persona: {}", idPersona, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    

    
    
}
