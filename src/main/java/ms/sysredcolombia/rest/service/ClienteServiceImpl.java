package ms.sysredcolombia.rest.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import ms.sysredcolombia.rest.modelo.repository.ClienteRepository;
import ms.sysredcolombia.rest.modelo.repository.ConfiguracionRepository;
import ms.sysredcolombia.rest.modelo.dtos.ClienteDtoView;
import ms.sysredcolombia.rest.modelo.dtos.CreateClienteDto;
import ms.sysredcolombia.rest.modelo.dtos.DtoConverter;
import ms.sysredcolombia.rest.modelo.entidades.Cliente;
import ms.sysredcolombia.rest.modelo.entidades.Configuracion;
import ms.sysredcolombia.rest.modelo.exceptions.NotFoundException;
import ms.sysredcolombia.rest.modelo.interfaces.ClienteInterface;

@AllArgsConstructor
@Service
public class ClienteServiceImpl implements ClienteInterface {

	private final ClienteRepository clienteRepository;
	private final ConfiguracionRepository configuracionRepository;

	@Override
	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	@Override
	public Page<Cliente> findAll(Pageable pageable) {
		return clienteRepository.findAll(pageable);
	}

	@Override
	public Configuracion saveConfiguracion(Configuracion configuracion) {
		return configuracionRepository.save(configuracion);

	}

	@Override
	public void save(Cliente cliente) {
		// TODO Auto-generated method stub

	}

	@Override
	public Cliente findOne(Integer id) {
		// TODO Auto-generated method stub
		return clienteRepository.getById(id);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Cliente findById(Integer id) {
		// if(clienteRepository.detalleCliente(id).isPresent())
		return null;

	}

	@Override
	public Cliente findFirstClienteByIdentificacion(String identificacion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cliente findFirstClienteByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cliente findFirstClienteByTelefono(String telefono) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cliente findFirstClienteByDiapago(int diapago) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cliente> findByDiaPagoBetween(int diaInicia, int diaFinal) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ClienteDtoView> listaClientesDto() {
		return clienteRepository.lista();
	}

	@Override
	public List<Configuracion> listaConfiguracion() {
		return configuracionRepository.findAll();
	}

	@Override
	public CreateClienteDto createCliente(CreateClienteDto createClienteDto) {
		Cliente cliente = clienteRepository.save(DtoConverter.dtoToCliente(createClienteDto));
		return DtoConverter.clienteToDto(cliente);

	}

	@Override
	public ClienteDtoView detalleCliente(int id) {
		return clienteRepository.detalleCliente(id)
				.orElseThrow(() -> new NotFoundException("Client not found - " + id, "S-301"));

	}

}
