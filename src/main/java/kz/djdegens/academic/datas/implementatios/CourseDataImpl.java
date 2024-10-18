package kz.djdegens.academic.datas.implementatios;

import kz.djdegens.academic.datas.interfaces.CourseData;
import kz.djdegens.academic.dtos.CourseDto;
import kz.djdegens.academic.entities.Course;
import kz.djdegens.academic.repositories.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseDataImpl implements CourseData {

    private final CourseRepository courseRepository;

    @Override
    public Course save(Course course) {
        if(Objects.isNull(course))throw new IllegalArgumentException("Course can not be null");
        return courseRepository.save(course);
    }

    @Override
    public Course findById(Long courseId) {
        if(courseId==null)throw new IllegalArgumentException("Course id can not be null");
        Optional<Course> optionalCourse = courseRepository.findById(courseId);
        if(optionalCourse.isEmpty())throw new NoSuchElementException("Course not found");
        return optionalCourse.get();
    }
}
