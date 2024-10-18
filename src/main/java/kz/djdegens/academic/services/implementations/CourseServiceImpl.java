package kz.djdegens.academic.services.implementations;

import kz.djdegens.academic.datas.interfaces.CourseData;
import kz.djdegens.academic.datas.interfaces.UserData;
import kz.djdegens.academic.dtos.CourseDto;
import kz.djdegens.academic.entities.Course;
import kz.djdegens.academic.entities.User;
import kz.djdegens.academic.mappers.CourseMapper;
import kz.djdegens.academic.services.interfaces.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final UserData userData;
    private final CourseData courseData;
    private final CourseMapper courseMapper;

    @Override
    public void addCourse(CourseDto courseDto) {
        if(courseDto.getCreatorId()==null)throw new IllegalArgumentException("Creator id can not be null");
        User creator = userData.findById(courseDto.getCreatorId());
        Course course = courseMapper.dtoToEntity(courseDto, creator);
        courseData.save(course);
    }
}
