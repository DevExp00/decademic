package kz.djdegens.academic.controllers;

import kz.djdegens.academic.dtos.ApplicationDto;
import kz.djdegens.academic.dtos.CourseDto;
import kz.djdegens.academic.dtos.ResultDto;
import kz.djdegens.academic.services.interfaces.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping("/course")
    public ResponseEntity<ApplicationDto> addCourse(@RequestBody CourseDto courseDto){
        try{
            courseService.addCourse(courseDto);
            return ResponseEntity.ok().body(ApplicationDto.builder()
                            .result(ResultDto.builder()
                                    .status("200")
                                    .message("Course added successfully")
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
