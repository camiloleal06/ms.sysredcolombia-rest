package ms.sysredcolombia.rest.modelo.dtos;

import org.springframework.beans.BeanUtils;

import ms.sysredcolombia.rest.modelo.entidades.Cliente;
import ms.sysredcolombia.rest.modelo.entidades.Factura;
import ms.sysredcolombia.rest.modelo.entidades.Plan;

public class DtoConverter {
	
    public static Cliente dtoToCliente(CreateClienteDto dto){
        Cliente cliente = new Cliente();
        BeanUtils.copyProperties(dto,cliente);
        return cliente;
    }
    public static CreateClienteDto clienteToDto(Cliente cliente){
        CreateClienteDto clienteDto = new CreateClienteDto();
        BeanUtils.copyProperties(cliente,clienteDto);
        return clienteDto;
    }
    
    public static PlanDto plaToDto(Plan plan){
        PlanDto planDto = new PlanDto();
        BeanUtils.copyProperties(plan,planDto);
        return planDto;
    }
    public static Plan dtoToPlan(PlanDto dto){
        Plan plan = new Plan();
        BeanUtils.copyProperties(dto,plan);
        return plan;
    }
    
    public static Cliente clienteDtoViewToCliente(ClienteDtoView dto){
        Cliente cliente = new Cliente();
        BeanUtils.copyProperties(dto,cliente);
        return cliente;
    }
    public static ClienteDtoView clienteToDtoView(Cliente cliente){
        ClienteDtoView clienteDto = new ClienteDtoView();
        BeanUtils.copyProperties(cliente,clienteDto);
        return clienteDto;
    }
    
    public static Factura createFacturaDtoToFactura(CreateFacturaDto dto){
        Factura factura = new Factura();
        BeanUtils.copyProperties(dto,factura);
        return factura;
    }
    public static CreateFacturaDto facturaToCreateFacturaDto(Factura factura){
        CreateFacturaDto facturaDto = new CreateFacturaDto();
        BeanUtils.copyProperties(factura,facturaDto);
        return facturaDto;
    }

}
