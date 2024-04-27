package ms.sysredcolombia.rest.controller;

import java.util.List;

import me.legrange.mikrotik.ApiConnectionException;
import me.legrange.mikrotik.MikrotikApiException;
import ms.sysredcolombia.rest.mikrotik.MikrotikServiceImpl;
import ms.sysredcolombia.rest.mikrotik.modelo.Profile;
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
import ms.sysredcolombia.rest.modelo.interfaces.ClienteInterface;
import ms.sysredcolombia.rest.modelo.interfaces.FacturaInterface;

@RestController
@RequestMapping("/api/clientes/")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class ClienteController {
    private final ClienteInterface clienteInterface;
    private final FacturaInterface facturaInterface;
    private final MikrotikServiceImpl mikrotikInterface;

    @GetMapping
    public ResponseEntity<List<ClienteDtoView>> listarClientes() {
        return new ResponseEntity<>(clienteInterface.listaClientesDto(),
                HttpStatus.OK);
    }

    @GetMapping("profile")
    public ResponseEntity<List<Profile>> listarProfilesFromBd() {
        return new ResponseEntity<>(mikrotikInterface.getProfiles(),
                HttpStatus.OK);
    }

    @PostMapping
    public CreateClienteDto createCliente(
            @RequestBody CreateClienteDto createClienteDto) {
        return clienteInterface.createCliente(createClienteDto);

    }

    @GetMapping("{id}")
    public ResponseEntity<ClienteDtoView> clienteById(@PathVariable int id) {
        return new ResponseEntity<ClienteDtoView>(
                clienteInterface.detalleCliente(id), HttpStatus.OK);
    }

    @PostMapping("configuracion")
    public Configuracion createConfiguracion(
            @RequestBody Configuracion configuracion) {
        return clienteInterface.saveConfiguracion(configuracion);
    }

    @GetMapping("configuracion")
    public List<Configuracion> listaConfiguracion() {
        return clienteInterface.listaConfiguracion();
    }

    @GetMapping("facturas/{id}")
    public ResponseEntity<List<FacturaDto>> listarFacturaByClienteId(
            @PathVariable int id) {
        return new ResponseEntity<>(
                facturaInterface.listadoFacturasPorCliente(id), HttpStatus.OK);
    }

}
