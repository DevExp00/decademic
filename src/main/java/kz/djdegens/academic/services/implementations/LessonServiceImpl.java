package kz.djdegens.academic.services.implementations;

import kz.djdegens.academic.datas.interfaces.*;
import kz.djdegens.academic.dtos.ApplicationDto;
import kz.djdegens.academic.dtos.LessonDto;
import kz.djdegens.academic.entities.LessonCompletion;
import kz.djdegens.academic.entities.Module;
import kz.djdegens.academic.entities.QuizCompletion;
import kz.djdegens.academic.entities.User;
import kz.djdegens.academic.mappers.LessonMapper;
import kz.djdegens.academic.mappers.ModuleMapper;
import kz.djdegens.academic.services.interfaces.LessonService;
import kz.djdegens.academic.services.interfaces.ModuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final ModuleData moduleData;
    private final LessonData lessonData;
    private final LessonMapper lessonMapper;
    private final UserData userData;
    private final ModuleCompletionData moduleCompletionData;
    private final LessonCompletionData lessonCompletionData;
    private final QuizCompletionData quizCompletionData;
    private final ModuleService moduleService;

    @Override
    public void addLesson(LessonDto lessonDto) {
        if(lessonDto.getModuleId()==null)throw new IllegalArgumentException("Module id can not be null");
        Module module = moduleData.findById(lessonDto.getModuleId());
        lessonData.save(lessonMapper.dtoToEntity(lessonDto,module));
    }

    @Override
    public void startAttempt(Long lessonId, ApplicationDto applicationDto) {
        if(Objects.isNull(lessonId))throw new IllegalArgumentException("Lesson id can not be null");
        if(Objects.isNull(applicationDto.getUser()))throw new IllegalArgumentException("User can not be null");
        if(Objects.isNull(applicationDto.getModuleCompletion()))throw new IllegalArgumentException("Module completion can not be null");
        User student = userData.findById(applicationDto.getUser().getId());
        if(student.getRole().equals("instructor"))throw new RuntimeException("Instructor can not start lesson");
        LessonCompletion lessonCompletion = new LessonCompletion();
        lessonCompletion.setModuleCompletion(moduleCompletionData.findById(applicationDto.getModuleCompletion().getId()));
        lessonCompletion.setUser(student);
        lessonCompletion.setLesson(lessonData.findById(lessonId));
        lessonCompletionData.save(lessonCompletion);
    }


    @Override
    public void handleLessonCompletion(QuizCompletion quizCompletion) {
        if(Objects.isNull(quizCompletion))throw new IllegalArgumentException("QuizCompletion completion can not be null");
        LessonCompletion lessonCompletion = quizCompletion.getLessonCompletion();
        Integer score = lessonCompletion.getScore();
        Integer scoreToPass = lessonCompletion.getLesson().getPointsToPass();
        if(score>=scoreToPass){
            lessonCompletion.setIsPassed(true);
            lessonCompletion.setCompletedAt(new Date());
            lessonCompletion.getModuleCompletion().setScore(lessonCompletion.getModuleCompletion().getScore() + score);
            lessonCompletionData.save(lessonCompletion);
            moduleService.handleModuleCompletion(lessonCompletion);
        }
    }
}
