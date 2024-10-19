package kz.djdegens.academic.services.implementations;

import kz.djdegens.academic.datas.interfaces.CourseCompletionData;
import kz.djdegens.academic.datas.interfaces.CourseData;
import kz.djdegens.academic.datas.interfaces.ModuleData;
import kz.djdegens.academic.datas.interfaces.UserData;
import kz.djdegens.academic.dtos.ApplicationDto;
import kz.djdegens.academic.dtos.CourseDto;
import kz.djdegens.academic.entities.Course;
import kz.djdegens.academic.entities.CourseCompletion;
import kz.djdegens.academic.entities.User;
import kz.djdegens.academic.mappers.CourseMapper;
import kz.djdegens.academic.mappers.ModuleMapper;
import kz.djdegens.academic.mappers.UserMapper;
import kz.djdegens.academic.services.interfaces.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final UserData userData;
    private final CourseData courseData;
    private final CourseMapper courseMapper;
    private final UserMapper userMapper;
    private final ModuleMapper moduleMapper;
    private final ModuleData moduleData;
    private final CourseCompletionData courseCompletionData;

    @Override
    public void addCourse(CourseDto courseDto) {
        if(courseDto.getCreatorId()==null)throw new IllegalArgumentException("Creator id can not be null");
        User creator = userData.findById(courseDto.getCreatorId());
        Course course = courseMapper.dtoToEntity(courseDto, creator);
        courseData.save(course);
    }

    @Override
    public ApplicationDto getCourse(Long courseId) {
        Course course = courseData.findById(courseId);
        return ApplicationDto.builder()
                .course(courseMapper.entityToDto(course))
                .user(userMapper.entityToDto(course.getCreator()))
                .modules(moduleMapper.entityToDto(moduleData.findAllByCourseId(course.getId())))
                .build();
    }

    @Override
    public void startAttempt(Long courseId,ApplicationDto applicationDto) {
        if (Objects.isNull(applicationDto))throw new IllegalArgumentException("Application dto can not be null");
        if(Objects.isNull(applicationDto.getUser().getLogin()))throw new IllegalArgumentException("User login can not be null");
        if(Objects.isNull(courseId))throw new IllegalArgumentException("Course id can not be null");
        Course course = courseData.findById(courseId);
        User student = userData.findById(applicationDto.getUser().getId());
        if(student.getRole().equals("instructor"))throw new RuntimeException("Instructor can not start course");
        CourseCompletion courseCompletion = new CourseCompletion();
        courseCompletion.setCourse(course);
        courseCompletion.setUser(student);
        courseCompletionData.save(courseCompletion);
    }
}
