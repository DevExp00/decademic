package kz.djdegens.academic.services.implementations;

import kz.djdegens.academic.datas.interfaces.UserData;
import kz.djdegens.academic.dtos.ApplicationDto;
import kz.djdegens.academic.dtos.TeamDto;
import kz.djdegens.academic.entities.Team;
import kz.djdegens.academic.entities.TeamUser;
import kz.djdegens.academic.entities.User;
import kz.djdegens.academic.repositories.TeamRepository;
import kz.djdegens.academic.repositories.TeamUserRepository;
import kz.djdegens.academic.services.interfaces.TeamService;
import kz.djdegens.academic.util.CodeGeneratorUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final UserData userData;
    private final TeamRepository teamRepository;
    private final TeamUserRepository teamUserRepository;

    @Override
    public ApplicationDto createTeam(ApplicationDto applicationDto) {
        if(Objects.isNull(applicationDto.getUser()))throw new IllegalArgumentException("User dto can not be null");
        User user = userData.findById(applicationDto.getUser().getId());
        Team team = new Team();
        team.setStatus("waiting");
        team.setCode(CodeGeneratorUtil.generateRandomCode());
        Team newTeam = teamRepository.save(team);

        TeamUser teamUser = new TeamUser();
        teamUser.setMember(user);
        teamUser.setTeam(team);

        teamUserRepository.save(teamUser);

        return ApplicationDto.builder()
                .teamDto(TeamDto.builder()
                        .id(newTeam.getId())
                        .build())
                .build();
    }

    @Override
    public ApplicationDto joinTeam(Long teamId, ApplicationDto applicationDto) {
        if(Objects.isNull(applicationDto.getUser()))throw new IllegalArgumentException("User can not be null");
        Optional<Team> optionalTeam = teamRepository.findById(teamId);
        if(optionalTeam.isEmpty())throw new NoSuchElementException("Team not found");
        Team team = optionalTeam.get();
        TeamUser teamUser = teamUserRepository.findByTeamId(team.getId());
        User user = userData.findById(applicationDto.getUser().getId());
        teamUser.setTeam(team);
        teamUser.setMember(user);
        teamUserRepository.save(teamUser);
        return ApplicationDto.builder()
                .teamDto(TeamDto.builder()
                        .id(team.getId())
                        .build())
                .build();
    }
}
