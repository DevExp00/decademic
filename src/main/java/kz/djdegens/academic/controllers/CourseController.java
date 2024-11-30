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
            courseService.addCourse(courseDto);
            return ResponseEntity.ok(ApplicationDto.builder().result(ResultDto.builder().message("Course added successfully").status("200").build()).build());
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

    @DeleteMapping("/{courseId}")
    public ResponseEntity<ApplicationDto> deleteCourse(@PathVariable Long courseId){
        try{
            courseService.deleteCourse(courseId);
            return ResponseEntity.ok(ApplicationDto.okResult("Course deleted successfully"));
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(ApplicationDto.builder()
                    .result(ResultDto.builder()
                            .message(e.getLocalizedMessage())
                            .status("500")
                            .build())
                    .build());
        }
    }

    @PutMapping("/{courseId}")
    public ResponseEntity<ApplicationDto> editCourse(@PathVariable Long courseId,
                                                     @RequestBody CourseDto courseDto){
        try{
            courseService.editCourse(courseId, courseDto);
            return ResponseEntity.ok(ApplicationDto.okResult("Course edited successfully"));
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
