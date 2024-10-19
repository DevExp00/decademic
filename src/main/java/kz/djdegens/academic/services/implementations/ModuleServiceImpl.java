package kz.djdegens.academic.services.implementations;

import kz.djdegens.academic.datas.interfaces.*;
import kz.djdegens.academic.dtos.ApplicationDto;
import kz.djdegens.academic.dtos.CourseDto;
import kz.djdegens.academic.dtos.ModuleDto;
import kz.djdegens.academic.entities.*;
import kz.djdegens.academic.entities.Module;
import kz.djdegens.academic.mappers.LessonMapper;
import kz.djdegens.academic.mappers.ModuleMapper;
import kz.djdegens.academic.services.interfaces.CourseService;
import kz.djdegens.academic.services.interfaces.ModuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ModuleServiceImpl implements ModuleService {

    private final CourseData courseData;
    private final ModuleData moduleData;
    private final ModuleMapper moduleMapper;
    private final LessonMapper lessonMapper;
    private final LessonData lessonData;
    private final CourseCompletionData courseCompletionData;
    private final UserData userData;
    private final ModuleCompletionData moduleCompletionData;
    private final CourseService courseService;

    @Override
    public ApplicationDto addModule(ModuleDto moduleDto) {
        if(moduleDto.getCourseId()==null)throw new IllegalArgumentException("Course id can not be null");
        Course course = courseData.findById(moduleDto.getCourseId());
        Module module = moduleData.save(moduleMapper.dtoToEntity(moduleDto,course));
        moduleDto = new ModuleDto();
        moduleDto.setId(module.getId());
        return ApplicationDto.builder()
                .module(moduleDto)
                .build();
    }

    @Override
    public ApplicationDto getModule(Long moduleId) {
        Module module = moduleData.findById(moduleId);
        return ApplicationDto.builder()
                .module(moduleMapper.entityToDto(module))
                .lessons(lessonMapper.entityToDto(lessonData.findAllByModuleId(module.getId())))
                .build();
    }

    @Override
    public void startAttempt(Long moduleId, ApplicationDto applicationDto) {
        if(Objects.isNull(moduleId))throw new IllegalArgumentException("Module id can not be null");
        if(Objects.isNull(applicationDto.getCourseCompletion().getId()))throw new IllegalArgumentException("Course completion id can not be null");
        if(Objects.isNull(applicationDto.getUser()))throw new IllegalArgumentException("Student can not be null");
        Module module = moduleData.findById(moduleId);
        User student = userData.findById(applicationDto.getUser().getId());
        if(student.getRole().equals("instructor"))throw new RuntimeException("Instructor can not start module");
        CourseCompletion courseCompletion = courseCompletionData.findById(applicationDto.getCourseCompletion().getId());
        ModuleCompletion moduleCompletion = new ModuleCompletion();
        moduleCompletion.setModule(module);
        moduleCompletion.setCourseCompletion(courseCompletion);
        moduleCompletion.setUser(student);
        moduleCompletionData.save(moduleCompletion);
    }

    @Override
    public void handleModuleCompletion(LessonCompletion lessonCompletion) {
        if(Objects.isNull(lessonCompletion))throw new IllegalArgumentException("Lesson completion can not be null");
        ModuleCompletion moduleCompletion = lessonCompletion.getModuleCompletion();
        Integer score = moduleCompletion.getScore();
        Integer scoreToPass = moduleCompletion.getModule().getPointsToPass();
        if(score >= scoreToPass){
            moduleCompletion.setIsPassed(true);
            moduleCompletion.setCompletedAt(new Date());
            moduleCompletion.getCourseCompletion().setScore(score + moduleCompletion.getCourseCompletion().getScore());
            moduleCompletionData.save(moduleCompletion);
            courseService.handleCourseCompletion(moduleCompletion);
        }
    }
}
