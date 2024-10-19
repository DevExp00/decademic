package kz.djdegens.academic.controllers;

import kz.djdegens.academic.dtos.AnswerDto;
import kz.djdegens.academic.dtos.ApplicationDto;
import kz.djdegens.academic.dtos.QuestionDto;
import kz.djdegens.academic.dtos.ResultDto;
import kz.djdegens.academic.services.interfaces.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/answers")
@RequiredArgsConstructor
public class AnswerController {

    private final AnswerService answerService;

    @PostMapping("/answer")
    private ResponseEntity<ApplicationDto> addAnswer(@RequestBody AnswerDto answerDto){
        try{
            return ResponseEntity.ok(answerService.addAnswer(answerDto));
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
