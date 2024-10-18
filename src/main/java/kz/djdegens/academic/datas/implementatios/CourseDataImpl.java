package kz.djdegens.academic.datas.implementatios;

import kz.djdegens.academic.datas.interfaces.CourseData;
import kz.djdegens.academic.dtos.CourseDto;
import kz.djdegens.academic.entities.Course;
import kz.djdegens.academic.repositories.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CourseDataImpl implements CourseData {

    private final CourseRepository courseRepository;

    @Override
    public Course save(Course course) {
        if(Objects.isNull(course))throw new IllegalArgumentException("Course can not be null");
        return courseRepository.save(course);
    }
}
