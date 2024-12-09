package kz.djdegens.academic.controllers;

import kz.djdegens.academic.dtos.ApplicationDto;
import kz.djdegens.academic.dtos.QuizDto;
import kz.djdegens.academic.dtos.ResultDto;
import kz.djdegens.academic.services.interfaces.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/quizzes")
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;

    @PostMapping("/quiz")
    public ResponseEntity<ApplicationDto> addQuiz(@RequestBody QuizDto quizDto){
        try{
            quizService.addQuiz(quizDto);
            return ResponseEntity.ok(ApplicationDto.okResult("Quiz added successfully"));
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(ApplicationDto.builder()
                    .result(ResultDto.builder()
                            .message(e.getLocalizedMessage())
                            .status("500")
                            .build())
                    .build());
        }
    }

    @PostMapping("/{quizId}/attempt")
    public ResponseEntity<ApplicationDto> startAttempt(@PathVariable Long quizId,
                                                        @RequestBody ApplicationDto applicationDto){
        try{
            quizService.startAttempt(quizId, applicationDto);
            return ResponseEntity.ok().body(ApplicationDto.builder()
                    .result(ResultDto.builder()
                            .status("200")
                            .message("Quiz started successfully")
                            .build())
                    .build());
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(ApplicationDto.builder()
                    .result(ResultDto.builder()
                            .message(e.getLocalizedMessage())
                            .status("500")
                            .build())
                    .build());
        }
    }

    @GetMapping("/{quizId}")
    public ResponseEntity<ApplicationDto> getQuiz(@PathVariable Long quizId){
        try{
            return ResponseEntity.ok(quizService.getQuiz(quizId));
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(ApplicationDto.builder()
                    .result(ResultDto.builder()
                            .message(e.getLocalizedMessage())
                            .status("500")
                            .build())
                    .build());
        }
    }

    @PutMapping("/{quizId}")
    public ResponseEntity<ApplicationDto> editQuiz(@PathVariable Long quizId,
                                                   @RequestBody QuizDto quizDto){
        try{
            quizService.editQuiz(quizId, quizDto);
            return ResponseEntity.ok(ApplicationDto.okResult("Quiz edited successfully"));
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
