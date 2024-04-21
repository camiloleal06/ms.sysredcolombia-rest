package ms.sysredcolombia.rest.modelo.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ms.sysredcolombia.rest.modelo.enums.EstadoCliente;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteDtoView {
    private int id;
    private String nombres;
    private String email;
    private String telefono;
    private EstadoCliente estado;
    private double precio;  
    private String nombre; 
    private String direccion;
    
  }
