package ms.sysredcolombia.rest.modelo.interfaces;

import java.util.List;

import ms.sysredcolombia.rest.modelo.dtos.CreateFacturaDto;
import ms.sysredcolombia.rest.modelo.dtos.FacturaDto;

public interface FacturaInterface {

	List<FacturaDto> listadoFacturas();

	List<FacturaDto> listadoFacturasPorCliente(int id);

	CreateFacturaDto createFactura(CreateFacturaDto facturaDto);
}
