package ms.sysredcolombia.rest.modelo.interfaces;

import java.util.List;

import ms.sysredcolombia.rest.modelo.dtos.PlanDto;
import ms.sysredcolombia.rest.modelo.entidades.Plan;

public interface PlanInterface {
	List<Plan> findAllPlanes();
	PlanDto createPlan (PlanDto plan);
}
