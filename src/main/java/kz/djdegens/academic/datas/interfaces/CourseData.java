package kz.djdegens.academic.datas.interfaces;

import kz.djdegens.academic.dtos.CourseDto;
import kz.djdegens.academic.entities.Course;

import java.util.List;

public interface CourseData {

    Course save(Course course);

    Course findById(Long courseId);

    List<Course> findAll();

    List<Course> findAllByCreatorId(Long creatorId);

}
