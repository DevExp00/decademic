package kz.djdegens.academic.controllers;

import kz.djdegens.academic.dtos.ApplicationDto;
import kz.djdegens.academic.dtos.ModuleDto;
import kz.djdegens.academic.dtos.ResultDto;
import kz.djdegens.academic.services.interfaces.ModuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/modules")
@RequiredArgsConstructor
public class ModuleController {

    private final ModuleService moduleService;

    @PostMapping("/module")
    public ResponseEntity<ApplicationDto> addModule(@RequestBody ModuleDto moduleDto){
        try{
            return ResponseEntity.ok(moduleService.addModule(moduleDto));
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(ApplicationDto.builder()
                            .result(ResultDto.builder()
                                    .status("500")
                                    .message(e.getLocalizedMessage())
                                    .build())
                    .build());
        }
    }

    @GetMapping("/{moduleId}")
    public ResponseEntity<ApplicationDto> getModule(@PathVariable Long moduleId){
        try{
            return ResponseEntity.ok(moduleService.getModule(moduleId));
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(ApplicationDto.builder()
                    .result(ResultDto.builder()
                            .status("500")
                            .message(e.getLocalizedMessage())
                            .build())
                    .build());
        }
    }

    @PostMapping("/{moduleId}/attempt")
    public ResponseEntity<ApplicationDto> startAttempt(@PathVariable Long moduleId,
                                                       @RequestBody ApplicationDto applicationDto){
        try{
            moduleService.startAttempt(moduleId, applicationDto);
            return ResponseEntity.ok().body(ApplicationDto.builder()
                    .result(ResultDto.builder()
                            .message("Module attempt started successfully")
                            .status("200")
                            .build())
                    .build());
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(ApplicationDto.builder()
                    .result(ResultDto.builder()
                            .status("500")
                            .message(e.getLocalizedMessage())
                            .build())
                    .build());
        }
    }
}
