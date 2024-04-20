package ms.sysredcolombia.rest.modelo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ms.sysredcolombia.rest.modelo.entidades.Configuracion;
import ms.sysredcolombia.rest.modelo.entidades.Plan;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateClienteDto {
	    private String identificacion;
	    private int diapago;
	    private String nombres;
	    private String apellidos;
	    private String email;
	    private String telefono;
	    private String direccion;
	    private String velocidad;
		private String pppoeuser;
		private String pppoepassword;
		private Configuracion configuracion;
	    private Plan plan;   
  
}
