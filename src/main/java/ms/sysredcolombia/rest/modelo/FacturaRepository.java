package ms.sysredcolombia.rest.modelo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ms.sysredcolombia.rest.modelo.dtos.FacturaDto;
import ms.sysredcolombia.rest.modelo.entidades.Factura;

public interface FacturaRepository extends JpaRepository<Factura, Integer>{

    @Query(value = "SELECT sum(valor) FROM Factura WHERE estado=true and periodo=MONTH(CURRENT_TIMESTAMP)")
    public Long totalFacturasPendientesMes();

    @Query(value = "SELECT count(*) FROM Factura WHERE estado=true and periodo=MONTH(CURRENT_TIMESTAMP)")
    public Long totalCantidadFacturasMes();

    @Query(value = "SELECT sum(valor) FROM Factura WHERE estado=false and periodo=MONTH(CURRENT_TIMESTAMP)")
    public Long totalFacturasPagadasMes();

    @Query(value = "SELECT sum(valor) FROM Factura WHERE estado=true")
    public Long totalFacturasPendientesHistorico();

    @Query(value = "SELECT count(*) FROM Factura WHERE estado=true")
    public Long totalCantidadFacturasHistorico();

    @Query(value = "SELECT sum(valor) FROM Factura WHERE estado=false")
    public Long totalFacturasPagadasHistorico();
    
   /* @Query("SELECT new ms.sysredcolombia.rest.modelo.dtos.FacturaDto(f.id, CONCAT(c.nombres,' ',c.apellidos) as nombres, "
            + "c.telefono, f.valor, c.diapago, DATEDIFF(CURRENT_TIMESTAMP() , f.fechaVencimiento) as mora), f.estado "
            + " FROM Factura f JOIN f.cliente c WHERE f.estado=true ")
    List<FacturaDto> listadoFacturasPendientes();*/
    
    
	@Query("SELECT new ms.sysredcolombia.rest.modelo.dtos.FacturaDto(f.id, DATEDIFF(CURRENT_TIMESTAMP(), f.fechaVencimiento) as mora, f.estado) "
			+ "FROM Factura f, Cliente c"
			+ " WHERE c.id=f.clienteId and c.id=?1")
    List<FacturaDto> listadoFacturasPorCliente(int id);
}
