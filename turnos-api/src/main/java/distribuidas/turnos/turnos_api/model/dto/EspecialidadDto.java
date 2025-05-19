package distribuidas.turnos.turnos_api.model.dto;

import java.math.BigDecimal;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EspecialidadDto {

    private Integer idEspecialidad;
    private String nombre;
    private BigDecimal precio;
    private Boolean estado;
    private String dias;
}
