package ms.sysredcolombia.rest.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import ms.sysredcolombia.rest.modelo.dtos.ClienteDtoView;
import ms.sysredcolombia.rest.modelo.dtos.CreateClienteDto;
import ms.sysredcolombia.rest.modelo.dtos.FacturaDto;
import ms.sysredcolombia.rest.modelo.entidades.Configuracion;
import ms.sysredcolombia.rest.modelo.enums.EstadoCliente;
import ms.sysredcolombia.rest.modelo.interfaces.ClienteInterface;
import ms.sysredcolombia.rest.modelo.interfaces.FacturaInterface;

@RestController
@RequestMapping("/api/clientes/")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class ClienteController {
	private final ClienteInterface clienteInterface;
	private final FacturaInterface facturaInterface;

	@GetMapping
	public ResponseEntity<List<ClienteDtoView>> listarClientes() {
		return new ResponseEntity<List<ClienteDtoView>>(clienteInterface.listaClientesDto(), HttpStatus.OK);
	}

	@GetMapping("prueba")
	public ResponseEntity<List<ClienteDtoView>> listarClientesPruebas() {
		List<ClienteDtoView> lista =	List.of(ClienteDtoView.builder()
		.direccion("Olaya")
		.email("camilo@gmail.com")
		.estado(EstadoCliente.ACTIVO)
		.nombre("Camilo Leal")
		.id(1).nombre("INterne Fibra")
		.build(),ClienteDtoView.builder()
		.direccion("Olaya")
		.email("camilo@gmail.com")
		.estado(EstadoCliente.ACTIVO)
		.nombre("Orfa PAtiño")
		.id(2).nombre("INterne Fibra")
		.build());
		
		return new ResponseEntity<List<ClienteDtoView>>(lista, HttpStatus.OK);
	}

	@PostMapping
	public CreateClienteDto createCliente(@RequestBody CreateClienteDto createClienteDto) {
		return clienteInterface.createCliente(createClienteDto);

	}

	@GetMapping("{id}")
	public ResponseEntity<ClienteDtoView> clienteById(@PathVariable int id) {
	   return new ResponseEntity<ClienteDtoView>(clienteInterface.detalleCliente(id),HttpStatus.OK);
	}

	@PostMapping("configuracion")
	public Configuracion createConfiguracion(@RequestBody Configuracion configuracion) {
		return clienteInterface.saveConfiguracion(configuracion);
	}

	@GetMapping("configuracion")
	public List<Configuracion> listaConfiguracion() {
	    return clienteInterface.listaConfiguracion();
	}
	@GetMapping("facturas/{id}")
	public ResponseEntity<List<FacturaDto>> listarFacturaByClienteId(@PathVariable int id) {
		return new ResponseEntity<List<FacturaDto>>(facturaInterface.listadoFacturasPorCliente(id),
				HttpStatus.OK);
	}

}
