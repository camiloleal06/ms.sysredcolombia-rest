package ms.sysredcolombia.rest.modelo.dtos;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ms.sysredcolombia.rest.modelo.entidades.Cliente;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateFacturaDto {
	private boolean estado;
	private String valor;
	private int periodo;
	private int clienteId;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaCreacion;	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaVencimiento;
}
