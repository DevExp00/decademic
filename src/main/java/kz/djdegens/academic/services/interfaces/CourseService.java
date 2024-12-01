package kz.djdegens.academic.services.interfaces;

import kz.djdegens.academic.dtos.ApplicationDto;
import kz.djdegens.academic.dtos.CourseDto;
import kz.djdegens.academic.entities.ModuleCompletion;

public interface CourseService {

    void addCourse(CourseDto courseDto);

    ApplicationDto getCourse(Long courseId);

    void editCourse(Long courseId,CourseDto courseDto);

    void buyCourse(Long courseId, ApplicationDto applicationDto);

    void startAttempt(Long courseId,ApplicationDto applicationDto);

    void handleCourseCompletion(ModuleCompletion moduleCompletion);

    ApplicationDto getCourses();

    ApplicationDto getCoursesByUserId(Long userId);
}
