package kz.djdegens.academic.services.interfaces;

import kz.djdegens.academic.dtos.ApplicationDto;
import kz.djdegens.academic.entities.Team;

public interface TeamService {

    ApplicationDto createTeam(ApplicationDto applicationDto);

    ApplicationDto joinTeam(Long teamId,ApplicationDto applicationDto);
}
