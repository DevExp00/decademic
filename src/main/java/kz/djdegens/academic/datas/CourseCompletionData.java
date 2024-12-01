package kz.djdegens.academic.datas;

import kz.djdegens.academic.entities.CourseCompletion;
import kz.djdegens.academic.repositories.CourseCompletionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CourseCompletionData {

    private final CourseCompletionRepository courseCompletionRepository;

    public CourseCompletion save(CourseCompletion courseCompletion) {
        if(Objects.isNull(courseCompletion))throw new IllegalArgumentException("Course completion can not be null");
        return courseCompletionRepository.save(courseCompletion);
    }

    public CourseCompletion findById(Long courseCompletionId) {
        if(Objects.isNull(courseCompletionId))throw new IllegalArgumentException("CourseCompletion id can not be null");
        Optional<CourseCompletion> optionalCourseCompletion = courseCompletionRepository.findById(courseCompletionId);
        if(optionalCourseCompletion.isEmpty())throw new NoSuchElementException("Course completion not found");
        return optionalCourseCompletion.get();
    }
}
