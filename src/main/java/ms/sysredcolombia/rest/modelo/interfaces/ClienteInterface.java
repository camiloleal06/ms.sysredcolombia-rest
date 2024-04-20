package ms.sysredcolombia.rest.modelo.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ms.sysredcolombia.rest.modelo.dtos.ClienteDto;
import ms.sysredcolombia.rest.modelo.dtos.ClienteDtoView;
import ms.sysredcolombia.rest.modelo.dtos.CreateClienteDto;
import ms.sysredcolombia.rest.modelo.entidades.Cliente;
import ms.sysredcolombia.rest.modelo.entidades.Configuracion;

public interface ClienteInterface {

    List<Cliente> findAll();

    List<ClienteDtoView> listaClientesDto();
    
    Page<Cliente> findAll(Pageable pageable);
    
    CreateClienteDto createCliente(CreateClienteDto createClienteDto);

    void save(Cliente cliente);

    Cliente findOne(Integer id);

    void delete(Integer id);

    Cliente findById(Integer id);

    Cliente findFirstClienteByIdentificacion(String identificacion);

    Cliente findFirstClienteByEmail(String email);

    Cliente findFirstClienteByTelefono(String telefono);

    Cliente findFirstClienteByDiapago(int diapago);

    List<Cliente> findByDiaPagoBetween(int diaInicia, int diaFinal);

	Configuracion saveConfiguracion(Configuracion configuracion);

	List<Configuracion> listaConfiguracion();

	ClienteDtoView detalleCliente(int id);
}
