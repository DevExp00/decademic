package kz.djdegens.academic.mappers;

import kz.djdegens.academic.datas.interfaces.ModuleData;
import kz.djdegens.academic.dtos.CourseDto;
import kz.djdegens.academic.entities.Course;
import kz.djdegens.academic.entities.User;
import kz.djdegens.academic.entities.UserStorage;
import kz.djdegens.academic.repositories.UserStorageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class CourseMapper {

    private final ModuleData moduleData;
    private final UserStorageRepository userStorageRepository;

    public Course dtoToEntity(CourseDto courseDto, User user){
        if(Objects.isNull(courseDto))throw new IllegalArgumentException("Course dto can not be null");
        if(Objects.isNull(user))throw new IllegalArgumentException("User can not be null");
        Course course = new Course();
        course.setTitle(courseDto.getTitle() == null ? null : courseDto.getTitle());
        course.setDescription(courseDto.getDescription() == null ? null : courseDto.getDescription());
        course.setCreator(user);
        course.setPointsToPass(courseDto.getPointsToPass() == null ? null : Integer.valueOf(courseDto.getPointsToPass()));
        course.setPrice(courseDto.getPrice() == null ? null : courseDto.getPrice());
        return course;
    }

    public CourseDto entityToDto(Course course){
        if(Objects.isNull(course))throw new IllegalArgumentException("Course can not be null");
        return CourseDto.builder()
                .id(course.getId())
                .title(course.getTitle() == null ? null : course.getTitle())
                .description(course.getDescription() == null ? null : course.getDescription())
                .pointsToPass(course.getPointsToPass() == null ? null : String.valueOf(course.getPointsToPass()))
                .price(course.getPrice() == null ? null : course.getPrice())
                .build();
    }

    public List<CourseDto> entityToDto(List<Course> courses){
        List<CourseDto> courseDtos = new ArrayList<>();
        for(Course course : courses){
            courseDtos.add(CourseDto.builder()
                    .id(course.getId())
                    .title(course.getTitle()== null ? null : course.getTitle())
                    .description(course.getDescription() == null ? null : course.getDescription())
                    .price(course.getPrice() == null ? null : course.getPrice())
                    .rate(course.getRate() == null ? null : course.getRate())
                    .countModules(moduleData.countAllByCourseId(course.getId()))
                    .followers(userStorageRepository.countAllByCourseId(course.getId()))
                    .build());
        }
        return courseDtos;
    }

    public Course dtoToEntity(Course course, CourseDto courseDto){
        if(Objects.isNull(course))throw new IllegalArgumentException("Course can not be null");
        if(Objects.isNull(courseDto))throw new IllegalArgumentException("Course dto can not be null");
        course.setPrice(courseDto.getPrice() == null ? course.getPrice() : courseDto.getPrice());
        course.setTitle(courseDto.getTitle() == null ? course.getTitle() : courseDto.getTitle());
        course.setDescription(courseDto.getDescription() == null ? course.getDescription() : courseDto.getDescription());
        course.setPointsToPass(courseDto.getPointsToPass() == null ? course.getPointsToPass() : Integer.valueOf(courseDto.getPointsToPass()));
        return course;
    }
}
