package kz.djdegens.academic.controllers;

import kz.djdegens.academic.dtos.ApplicationDto;
import kz.djdegens.academic.dtos.LessonDto;
import kz.djdegens.academic.dtos.ResultDto;
import kz.djdegens.academic.services.interfaces.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/lessons")
@RequiredArgsConstructor
public class LessonController {

    private final LessonService lessonService;

    @PostMapping("/lesson")
    public ResponseEntity<ApplicationDto> addLesson(@RequestBody LessonDto lessonDto){
        try{
            lessonService.addLesson(lessonDto);
            return ResponseEntity.ok().body(ApplicationDto.builder()
                            .result(ResultDto.builder()
                                    .status("200")
                                    .message("Lesson added successfully")
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
