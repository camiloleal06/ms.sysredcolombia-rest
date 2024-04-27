package ms.sysredcolombia.rest.mikrotik.repository;

import ms.sysredcolombia.rest.mikrotik.modelo.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile,Integer> {
}
