package ms.sysredcolombia.rest.modelo;

import org.springframework.data.jpa.repository.JpaRepository;

import ms.sysredcolombia.rest.modelo.entidades.Plan;

public interface PlanRepository extends JpaRepository<Plan, Integer> {

}
