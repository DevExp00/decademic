package kz.djdegens.academic.controllers;

import kz.djdegens.academic.dtos.ApplicationDto;
import kz.djdegens.academic.dtos.ResultDto;
import kz.djdegens.academic.services.interfaces.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/teams")
public class TeamController {

    private final TeamService teamService;

    @PostMapping("/team")
    public ResponseEntity<ApplicationDto> createTeam(@RequestBody ApplicationDto applicationDto){
        try{
            return ResponseEntity.ok(teamService.createTeam(applicationDto));
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(ApplicationDto.builder()
                    .result(ResultDto.builder()
                            .message(e.getLocalizedMessage())
                            .status("500")
                            .build())
                    .build());
        }
    }

    @PostMapping("/{teamId}/join")
    public ResponseEntity<ApplicationDto> joinTeam(@RequestBody ApplicationDto applicationDto,
                                                   @PathVariable Long teamId){
        try{
            return ResponseEntity.ok(teamService.joinTeam(teamId,applicationDto));
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(ApplicationDto.builder()
                    .result(ResultDto.builder()
                            .message(e.getLocalizedMessage())
                            .status("500")
                            .build())
                    .build());
        }
    }
}
