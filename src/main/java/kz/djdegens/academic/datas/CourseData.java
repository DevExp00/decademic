package kz.djdegens.academic.datas;

import kz.djdegens.academic.entities.Course;
import kz.djdegens.academic.repositories.CourseRepository;
import kz.djdegens.academic.repositories.specifications.CourseSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CourseData {

    private final CourseRepository courseRepository;

    public Course save(Course course) {
        if(Objects.isNull(course))throw new IllegalArgumentException("Course can not be null");
        return courseRepository.save(course);
    }

    public Course findById(Long courseId) {
        if(courseId==null)throw new IllegalArgumentException("Course id can not be null");
        Optional<Course> optionalCourse = courseRepository.findById(courseId);
        if(optionalCourse.isEmpty())throw new NoSuchElementException("Course not found");
        return optionalCourse.get();
    }

    public List<Course> findAll() {
        return courseRepository.findAll(Specification.where(CourseSpecification.findByIsActive(true)));
    }

    public List<Course> findAllByCreatorId(Long creatorId) {
        if(Objects.isNull(creatorId))throw new IllegalArgumentException("Creator id can not be null");
        return courseRepository.findAll(Specification.where(CourseSpecification.findByCreatorId(creatorId)).and(CourseSpecification.findByIsActive(true)));
    }

    public void deleteCourse(Long courseId) {
        if(courseId==null)throw new IllegalArgumentException("Course id can not be null");
        Optional<Course> optionalCourse = courseRepository.findById(courseId);
        if(optionalCourse.isEmpty())throw new NoSuchElementException("Course not found");
        courseRepository.delete(optionalCourse.get());
    }
}
