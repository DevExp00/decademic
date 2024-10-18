package kz.djdegens.academic.services.implementations;

import kz.djdegens.academic.dtos.CourseDto;
import kz.djdegens.academic.services.interfaces.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    @Override
    public void addCourse(CourseDto courseDto) {

    }
}
