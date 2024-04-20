package ms.sysredcolombia.rest.modelo.entidades;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ms.sysredcolombia.rest.modelo.enums.EstadoCliente;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@jakarta.persistence.Id
	@jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
	private int id;
	private int diapago;
	private String nombres;
	private String apellidos;
	private String email;
	private String telefono;
	private String direccion;
	private String velocidad;
	private String pppoeuser;
	private String pppoepassword;
	private EstadoCliente estado = EstadoCliente.ACTIVO;
	@Column(name = "create_at")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createAt;

	@ManyToOne(fetch = jakarta.persistence.FetchType.EAGER)
	private Plan plan;
	@ManyToOne(fetch = jakarta.persistence.FetchType.EAGER)
	private Configuracion configuracion;

	@PrePersist
	public void prePersist() {
		createAt = new Date();
	}
}
