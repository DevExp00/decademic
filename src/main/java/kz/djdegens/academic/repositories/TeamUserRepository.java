package kz.djdegens.academic.repositories;

import kz.djdegens.academic.entities.TeamUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamUserRepository extends JpaRepository<TeamUser , Long> {

    TeamUser findByTeamId(Long teamId);
}
