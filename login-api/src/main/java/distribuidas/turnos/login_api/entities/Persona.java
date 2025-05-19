package distribuidas.turnos.login_api.entities;

import java.io.Serializable;
 

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
 
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "persona", schema="log")
@NoArgsConstructor
public class Persona implements Serializable {
	
     private static final long serialVersionUID = 1L;

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_persona")
        private Integer idPersona;

        @Column(name = "nombre")
        private String nombre;

        @Column(name = "edad")
        private Integer edad;

        @Column(name= "cedula")
        private String cedula;

        @Column(name = "rol")
        private String rol;
}
