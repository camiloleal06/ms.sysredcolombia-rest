package ms.sysredcolombia.rest.modelo.dtos;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ms.sysredcolombia.rest.modelo.enums.EstadoCliente;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDto {
    private int id;
 //   private String identificacion;
    private String nombres;
    private String email;
    private String telefono;
   // private double valor;
    private EstadoCliente estado;
}