package kz.djdegens.academic.controllers;

import kz.djdegens.academic.dtos.ApplicationDto;
import kz.djdegens.academic.dtos.LessonDto;
import kz.djdegens.academic.dtos.ResultDto;
import kz.djdegens.academic.services.interfaces.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/lessons")
@RequiredArgsConstructor
public class LessonController {

    private final LessonService lessonService;

    @PostMapping("/lesson")
    public ResponseEntity<ApplicationDto> addLesson(@RequestBody LessonDto lessonDto){
        try{
            lessonService.addLesson(lessonDto);
            return ResponseEntity.ok(ApplicationDto.okResult("Lesson added successfully"));
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(ApplicationDto.builder()
                    .result(ResultDto.builder()
                            .message(e.getLocalizedMessage())
                            .status("500")
                            .build())
                    .build());
        }
    }

    @GetMapping("/{lessonId}")
    public ResponseEntity<ApplicationDto> getLesson(@PathVariable Long lessonId){
        try{
            return ResponseEntity.ok(lessonService.getLesson(lessonId));
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(ApplicationDto.builder()
                    .result(ResultDto.builder()
                            .message(e.getLocalizedMessage())
                            .status("500")
                            .build())
                    .build());
        }
    }

    @PutMapping("/{lessonId}")
    public ResponseEntity<ApplicationDto> editLesson(@PathVariable Long lessonId,
                                                     @RequestBody LessonDto lessonDto){
        try{
            lessonService.editLesson(lessonId, lessonDto);
            return ResponseEntity.ok(ApplicationDto.okResult("Lesson edited successfully"));
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(ApplicationDto.builder()
                    .result(ResultDto.builder()
                            .message(e.getLocalizedMessage())
                            .status("500")
                            .build())
                    .build());
        }
    }

    @PostMapping("/{lessonId}/attempt")
    public ResponseEntity<ApplicationDto> startAttempt(@PathVariable Long lessonId,
                                                       @RequestBody ApplicationDto applicationDto){
        try{
            lessonService.startAttempt(lessonId, applicationDto);
            return ResponseEntity.ok().body(ApplicationDto.builder()
                    .result(ResultDto.builder()
                            .status("200")
                            .message("Lesson started successfully")
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
}
