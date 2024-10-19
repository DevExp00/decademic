package kz.djdegens.academic.datas.implementatios;

import kz.djdegens.academic.datas.interfaces.CourseCompletionData;
import kz.djdegens.academic.entities.CourseCompletion;
import kz.djdegens.academic.repositories.CourseCompletionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseCompletionDataImpl implements CourseCompletionData {

    private final CourseCompletionRepository courseCompletionRepository;

    @Override
    public CourseCompletion save(CourseCompletion courseCompletion) {
        if(Objects.isNull(courseCompletion))throw new IllegalArgumentException("Course completion can not be null");
        return courseCompletionRepository.save(courseCompletion);
    }

    @Override
    public CourseCompletion findById(Long courseCompletionId) {
        if(Objects.isNull(courseCompletionId))throw new IllegalArgumentException("CourseCompletion id can not be null");
        Optional<CourseCompletion> optionalCourseCompletion = courseCompletionRepository.findById(courseCompletionId);
        if(optionalCourseCompletion.isEmpty())throw new NoSuchElementException("Course completion not found");
        return optionalCourseCompletion.get();
    }
}
