package kz.djdegens.academic.mappers;

import kz.djdegens.academic.dtos.CourseDto;
import kz.djdegens.academic.entities.Course;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CourseMapper {

    public Course dtoToEntity(CourseDto courseDto){
        if(Objects.isNull(courseDto))throw new IllegalArgumentException("Course dto can not be null")
    }
}
