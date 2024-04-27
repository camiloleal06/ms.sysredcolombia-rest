package ms.sysredcolombia.rest.modelo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ms.sysredcolombia.rest.modelo.dtos.ClienteDtoView;
import ms.sysredcolombia.rest.modelo.entidades.Cliente;

@Repository
public interface ClienteRepository  extends JpaRepository<Cliente, Integer>{
 
	/*@Query("SELECT new ms.sysredcolombia.rest.modelo.dtos.ClienteDto(c.id,  CONCAT(c.nombres,' ',c.apellidos), "
			+ "c.email, c.telefono, c.estado) "
			+ " FROM Cliente c  where c.estado=0")*/
	
	@Query("SELECT new ms.sysredcolombia.rest.modelo.dtos.ClienteDtoView(c.id, CONCAT(c.nombres,' ',c.apellidos), "
			+ "c.email, c.telefono, c.estado , p.precio,p.nombre, c.direccion, pf.profileName) "
			+ " FROM Cliente c JOIN c.plan p JOIN profile pf where c.estado=0")
	List<ClienteDtoView> lista();
	
	@Query("SELECT new ms.sysredcolombia.rest.modelo.dtos.ClienteDtoView(c.id,CONCAT(c.nombres,' ',c.apellidos), "
			+ "c.email, c.telefono, c.estado , p.precio,p.nombre, c.direccion, pf.profileName) "
			+ " FROM Cliente c JOIN c.plan p JOIN c.profile pf where c.id=?1")
	Optional<ClienteDtoView> detalleCliente(int id);
	
	
}
