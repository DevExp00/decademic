package kz.djdegens.academic.datas.interfaces;

import kz.djdegens.academic.dtos.CourseDto;
import kz.djdegens.academic.entities.Course;

public interface CourseData {

    Course save(Course course);

    Course findById(Long courseId);
}
