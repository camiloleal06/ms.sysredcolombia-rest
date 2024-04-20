package ms.sysredcolombia.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import ms.sysredcolombia.rest.modelo.dtos.CreateFacturaDto;
import ms.sysredcolombia.rest.modelo.interfaces.FacturaInterface;

@CrossOrigin
@RestController
@RequestMapping("/api/facturas")
@AllArgsConstructor
public class FacturaController {
	private final FacturaInterface facturaInterface;

	@PostMapping
	public ResponseEntity<CreateFacturaDto> createPlan(@RequestBody CreateFacturaDto createFacturaDto) {
		return new ResponseEntity<CreateFacturaDto>(facturaInterface.createFactura(createFacturaDto),
				HttpStatus.CREATED);

	}

}
