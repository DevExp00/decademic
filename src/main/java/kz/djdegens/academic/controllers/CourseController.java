package kz.djdegens.academic.controllers;

import kz.djdegens.academic.dtos.ApplicationDto;
import kz.djdegens.academic.dtos.CourseDto;
import kz.djdegens.academic.dtos.ResultDto;
import kz.djdegens.academic.services.interfaces.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping("/course")
    public ResponseEntity<ApplicationDto> addCourse(@RequestBody CourseDto courseDto){
        try{
            return ResponseEntity.ok(courseService.addCourse(courseDto));
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(ApplicationDto.builder()
                    .result(ResultDto.builder()
                            .message(e.getLocalizedMessage())
                            .status("500")
                            .build())
                    .build());
        }
    }

    @PostMapping("/{courseId}/attempt")
    public ResponseEntity<ApplicationDto> startAttempt(@PathVariable Long courseId,@RequestBody ApplicationDto applicationDto){
        try{
            courseService.startAttempt(courseId,applicationDto);
            return ResponseEntity.ok().body(ApplicationDto.builder()
                    .result(ResultDto.builder()
                            .status("200")
                            .message("Attempt started successfully")
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

    @GetMapping("/by-user")
    public ResponseEntity<ApplicationDto> getCoursesByUserId(@RequestParam Long userId){
        try{
            return ResponseEntity.ok(courseService.getCoursesByUserId(userId));
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(ApplicationDto.builder()
                    .result(ResultDto.builder()
                            .message(e.getLocalizedMessage())
                            .status("500")
                            .build())
                    .build());
        }
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<ApplicationDto> getCourse(@PathVariable Long courseId){
        try{
            return ResponseEntity.ok(courseService.getCourse(courseId));
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(ApplicationDto.builder()
                    .result(ResultDto.builder()
                            .message(e.getLocalizedMessage())
                            .status("500")
                            .build())
                    .build());
        }
    }

    @PostMapping("/{courseId}/buy")
    public ResponseEntity<ApplicationDto> buyCourse(@PathVariable Long courseId,
                                                    @RequestBody ApplicationDto applicationDto){
        try{

            return ResponseEntity.ok().body(ApplicationDto.builder()
                    .result(ResultDto.builder()
                            .status("200")
                            .message("Course bought successfully")
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

    @GetMapping("/")
    public ResponseEntity<ApplicationDto> getCourses(){
        try{
            return ResponseEntity.ok(courseService.getCourses());
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
