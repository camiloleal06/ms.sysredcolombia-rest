package ms.sysredcolombia.rest.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import ms.sysredcolombia.rest.modelo.repository.FacturaRepository;
import ms.sysredcolombia.rest.modelo.dtos.CreateFacturaDto;
import ms.sysredcolombia.rest.modelo.dtos.DtoConverter;
import ms.sysredcolombia.rest.modelo.dtos.FacturaDto;
import ms.sysredcolombia.rest.modelo.interfaces.FacturaInterface;

@AllArgsConstructor
@Service
public class FacturaServiceImpl implements FacturaInterface {

	private final FacturaRepository facturaRepository;

	@Override
	public List<FacturaDto> listadoFacturas() {
		return null;//facturaRepository.listadoFacturasPendientes();
	}

	@Override
	public List<FacturaDto> listadoFacturasPorCliente(int id) {
		return facturaRepository.listadoFacturasPorCliente(id);
	}
    
	@Override
	public CreateFacturaDto createFactura(CreateFacturaDto facturaDto) {
		return DtoConverter.facturaToCreateFacturaDto(
				this.facturaRepository.save(DtoConverter.createFacturaDtoToFactura(facturaDto)));

	}

}
