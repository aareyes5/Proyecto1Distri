package distribuidas.turnos.turnos_api.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "turno", schema = "hospital")
@NoArgsConstructor
@AllArgsConstructor
public class Turno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_turno")
    private Integer idTurno;
 
    @JoinColumn(name = "id_especialidad", referencedColumnName = "id_especialidad")
    @ManyToOne(optional = false)
    private Especialidad especialidad;

    @JoinColumn(name = "id_medico" , referencedColumnName = "id_medico")
    @ManyToOne(optional = false)
    private Medico medico;

   
    @Column(name = "id_paciente")
    private Integer paciente;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "hora")
    private LocalTime hora;
}
