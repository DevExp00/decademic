package kz.djdegens.academic.services.interfaces;

import kz.djdegens.academic.dtos.ApplicationDto;
import kz.djdegens.academic.dtos.CourseDto;

public interface CourseService {

    void addCourse(CourseDto courseDto);

    ApplicationDto getCourse(Long courseId);
}
