package distribuidas.turnos.login_api.entities;


import java.io.Serializable;
 

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
 
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "login", schema="log")
@NoArgsConstructor
public class Usuarios implements Serializable {
    
     private static final long serialVersionUID = 1L;

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_user")
        private Integer idUsuario;

        @Column(name = "usuario")
        private String usuario;

        @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
        @ManyToOne(optional = false)
        private Persona idPersona;

        @Column(name = "mail")
        private String mail;

        @Column(name = "contrasena")
        private String contrasena;

        @Column(name = "estado")
        private Boolean estado;

        

}
