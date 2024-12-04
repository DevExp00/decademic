package kz.djdegens.academic.controllers;

import kz.djdegens.academic.dtos.ApplicationDto;
import kz.djdegens.academic.dtos.QuestionDto;
import kz.djdegens.academic.dtos.ResultDto;
import kz.djdegens.academic.services.interfaces.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @PutMapping("/{questionId}")
    public ResponseEntity<ApplicationDto> editQuestion(@PathVariable Long questionId,
                                                       @RequestBody ApplicationDto applicationDto){
        try{
            questionService.editQuestion(questionId,applicationDto);
            return ResponseEntity.ok(ApplicationDto.okResult("Question edited successfully"));
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(ApplicationDto.builder()
                    .result(ResultDto.builder()
                            .message(e.getLocalizedMessage())
                            .status("500")
                            .build())
                    .build());
        }
    }

    @PostMapping("/attempt")
    public ResponseEntity<ApplicationDto> attemptQuestion(@RequestBody ApplicationDto applicationDto){
        try{
            return ResponseEntity.ok(questionService.attemptQuestion(applicationDto));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(ApplicationDto.builder()
                    .result(ResultDto.builder()
                            .message(e.getLocalizedMessage())
                            .status("500")
                            .build())
                    .build());
        }
    }
}
