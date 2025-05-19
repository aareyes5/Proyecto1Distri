package distribuidas.turnos.login_api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author: diegoquezada
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponseDto<T>  {


    //esta es la clase generica a  usar para todas las repsuestas d elos servicios, se debe definir los codigos de respuesta y los mensajes
    private String codigoRespuesta;
    private String mensajeRespuesta;
    private T objeto;

}