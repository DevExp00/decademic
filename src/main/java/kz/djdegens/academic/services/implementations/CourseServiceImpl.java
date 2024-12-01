package kz.djdegens.academic.services.implementations;

import kz.djdegens.academic.datas.*;
import kz.djdegens.academic.dtos.AnswerDto;
import kz.djdegens.academic.dtos.ApplicationDto;
import kz.djdegens.academic.dtos.CourseDto;
import kz.djdegens.academic.dtos.ModuleDto;
import kz.djdegens.academic.entities.*;
import kz.djdegens.academic.entities.Module;
import kz.djdegens.academic.mappers.CourseMapper;
import kz.djdegens.academic.mappers.LessonMapper;
import kz.djdegens.academic.mappers.ModuleMapper;
import kz.djdegens.academic.mappers.UserMapper;
import kz.djdegens.academic.repositories.UserStorageRepository;
import kz.djdegens.academic.services.interfaces.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

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
    private final UserStorageData userStorageData;
    private final UserStorageRepository userStorageRepository;

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
    public void deleteCourse(Long courseId) {
        courseData.deleteCourse(courseId);
    }

    @Override
    public void startAttempt(Long courseId,ApplicationDto applicationDto) {
        if (Objects.isNull(applicationDto))throw new IllegalArgumentException("Application dto can not be null");
        if(Objects.isNull(applicationDto.getUser().getLogin()))throw new IllegalArgumentException("User login can not be null");
        if(Objects.isNull(courseId))throw new IllegalArgumentException("Course id can not be null");
        Course course = courseData.findById(courseId);
        User student = userData.findById(applicationDto.getUser().getId());
        if(Objects.isNull(userStorageRepository.findByCourseIdAndStudentId(course.getId(), student.getId()))){
            throw new RuntimeException("User have no access to course");
        }
        if(student.getRole().equals("instructor"))throw new RuntimeException("Instructor can not start course");
        CourseCompletion courseCompletion = new CourseCompletion();
        courseCompletion.setCourse(course);
        courseCompletion.setUser(student);
        courseCompletionData.save(courseCompletion);
    }

    @Override
    public void handleCourseCompletion(ModuleCompletion moduleCompletion) {
        if(Objects.isNull(moduleCompletion))throw new IllegalArgumentException("Module completion can not be null");
        CourseCompletion courseCompletion = moduleCompletion.getCourseCompletion();
        Integer score = courseCompletion.getScore();
        Integer scoreToPass = courseCompletion.getCourse().getPointsToPass();
        if(score >= scoreToPass){
            courseCompletion.setIsPassed(true);
            courseCompletion.setCompletedAt(new Date());
            courseCompletionData.save(courseCompletion);
        }
    }

    @Override
    public void buyCourse(Long courseId, ApplicationDto applicationDto) {
        if(Objects.isNull(applicationDto.getUser()))throw new IllegalArgumentException("User dto can not be null");
        User user = userData.findById(applicationDto.getUser().getId());
        Course course = courseData.findById(courseId);
        UserStorage userStorage = new UserStorage();
        userStorage.setStudent(user);
        userStorage.setCourse(course);
        userStorageData.save(userStorage);
    }

    @Override
    public ApplicationDto getCourses() {
        List<Course> courses = courseData.findAll();
        return ApplicationDto.builder()
                .courses(courseMapper.entityToDto(courses))
                .build();
    }

    @Override
    public ApplicationDto getCoursesByUserId(Long userId) {
        User user = userData.findById(userId);
        List<Course> courses = courseData.findAllByCreatorId(user.getId());
        return ApplicationDto.builder()
                .courses(courseMapper.entityToDto(courses))
                .build();
    }

    @Override
    public void editCourse(Long courseId, CourseDto dto) {
        Course course = courseData.findById(courseId);
        courseData.save(courseMapper.dtoToEntity(course,dto));
    }

}
