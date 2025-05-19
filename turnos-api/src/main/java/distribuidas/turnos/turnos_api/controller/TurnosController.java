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

import java.time.LocalDate;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import jakarta.servlet.http.HttpServletRequest;
import distribuidas.turnos.turnos_api.entities.Medico;
// Import TurnoDto
import distribuidas.turnos.turnos_api.model.dto.TurnoDto;
import distribuidas.turnos.turnos_api.services.TurnoService;

import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/turnos")
public class TurnosController {

    @Autowired
    private HttpServletRequest request;

    private static final Logger logger = LoggerFactory.getLogger(EspecialidadController.class);

    @Autowired
    private TurnoService turnoService;

    @GetMapping("/{id}")
    public ResponseEntity<TurnoDto> getTurnoById(@PathVariable("id") Integer id) {
        logger.info("Buscando turno con id: {}", id);
        try {
            TurnoDto dto = turnoService.getTurnoById(id);
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            logger.error("Error al buscar turno con id: {}", id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<TurnoDto>> getAllTurnos() {
        logger.info("Buscando todos los turnos");
        try {
            List<TurnoDto> dto = turnoService.getAllTurnos();
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            logger.error("Error al buscar todos los turnos", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/medico/{idMedico}")
    public ResponseEntity<List<TurnoDto>> getTurnosByMedico(@PathVariable("idMedico") Medico idMedico) {
        logger.info("Buscando turnos por medico con id: {}", idMedico);
        try {
            List<TurnoDto> dto = turnoService.getAllTurnosByMedico(idMedico);
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            logger.error("Error al buscar turnos por medico con id: {}", idMedico, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/paciente/{idPaciente}")
    public ResponseEntity<List<TurnoDto>> getTurnosByPaciente(@PathVariable("idPaciente") Integer idPaciente) {
        logger.info("Buscando turnos por paciente con id: {}", idPaciente);
        try {
            List<TurnoDto> dto = turnoService.getAllTurnosByPaciente(idPaciente);
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            logger.error("Error al buscar turnos por paciente con id: {}", idPaciente, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<TurnoDto> saveTurno(@RequestBody TurnoDto turno) {
        logger.info("Guardando turno: {}", turno);
        try {
            TurnoDto dto = turnoService.saveTurno(turno);
            return ResponseEntity.status(HttpStatus.CREATED).body(dto);
        } catch (Exception e) {
            logger.error("Error al guardar turno", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TurnoDto> updateTurno(@PathVariable("id") Integer id, @RequestBody TurnoDto turno) {
        logger.info("Actualizando turno con id: {}", id);
        try {
            TurnoDto dto = turnoService.updateTurno(id, turno);
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            logger.error("Error al actualizar turno con id: {}", id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTurno(@PathVariable("id") Integer id) {
        logger.info("Eliminando turno con id: {}", id);
        try {
            turnoService.deleteTurno(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            logger.error("Error al eliminar turno con id: {}", id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/medico")
    public ResponseEntity<List<TurnoDto>> getTurnosByMedicoAndFecha(@RequestParam("idMedico") Medico idMedico, @RequestParam("fecha") LocalDate fecha) {
        logger.info("Buscando turnos por medico con id: {} y fecha: {}", idMedico, fecha);
        try {
            List<TurnoDto> dto = turnoService.getTurnosByMedicoAndFecha(idMedico, fecha);
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            logger.error("Error al buscar turnos por medico con id: {} y fecha: {}", idMedico, fecha, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/paciente")
    public ResponseEntity<List<TurnoDto>> getTurnosByPacienteAndFecha(@RequestParam("idPaciente") Integer idPaciente, @RequestParam("fecha") LocalDate fecha) {
        logger.info("Buscando turnos por paciente con id: {} y fecha: {}", idPaciente, fecha);
        try {
            List<TurnoDto> dto = turnoService.getTurnosByPacienteAndFecha(idPaciente, fecha);
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            logger.error("Error al buscar turnos por paciente con id: {} y fecha: {}", idPaciente, fecha, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    
}
