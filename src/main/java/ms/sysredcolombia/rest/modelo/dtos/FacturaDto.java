package ms.sysredcolombia.rest.modelo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FacturaDto {
	private int idFactura;
	private String nombres;
	private String telefonoCliente;
	private double valorFactura;
	private int diapago;
	private int mora;
	private boolean estado;

	public FacturaDto(int idFactura, int mora, boolean estado) {
		this.idFactura = idFactura;
		this.mora = mora;
		this.estado = estado;
	}
}
