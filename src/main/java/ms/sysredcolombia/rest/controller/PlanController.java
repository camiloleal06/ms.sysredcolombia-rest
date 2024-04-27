package ms.sysredcolombia.rest.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import ms.sysredcolombia.rest.modelo.dtos.PlanDto;
import ms.sysredcolombia.rest.modelo.entidades.Plan;
import ms.sysredcolombia.rest.modelo.interfaces.PlanInterface;

@CrossOrigin
@RestController
@RequestMapping("/api/planes")
@AllArgsConstructor
public class PlanController {
	private final PlanInterface planInterface;
	
	@GetMapping
	public List<Plan> listarClientes (){
	  return planInterface.findAllPlanes();
	}
	@PostMapping
	public PlanDto createPlan(@RequestBody PlanDto planDto) {
		return planInterface.createPlan(planDto);
		
	}

}
