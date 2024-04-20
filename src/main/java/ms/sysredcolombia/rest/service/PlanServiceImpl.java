package ms.sysredcolombia.rest.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import ms.sysredcolombia.rest.modelo.PlanRepository;
import ms.sysredcolombia.rest.modelo.dtos.DtoConverter;
import ms.sysredcolombia.rest.modelo.dtos.PlanDto;
import ms.sysredcolombia.rest.modelo.entidades.Cliente;
import ms.sysredcolombia.rest.modelo.entidades.Plan;
import ms.sysredcolombia.rest.modelo.interfaces.PlanInterface;

@AllArgsConstructor
@Service
public class PlanServiceImpl implements PlanInterface{
	
	private final PlanRepository planRepository;
		
	@Override
	public List<Plan> findAllPlanes() {
		return planRepository.findAll();
		}

	@Override
	public PlanDto createPlan(PlanDto planDto) {
		Plan plan = planRepository.save(DtoConverter.dtoToPlan(planDto));
		return DtoConverter.plaToDto(plan);
	}

}
