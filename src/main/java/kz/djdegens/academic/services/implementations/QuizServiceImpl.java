package kz.djdegens.academic.services.implementations;

import kz.djdegens.academic.datas.interfaces.LessonCompletionData;
import kz.djdegens.academic.datas.interfaces.LessonData;
import kz.djdegens.academic.datas.interfaces.QuizData;
import kz.djdegens.academic.datas.interfaces.UserData;
import kz.djdegens.academic.dtos.ApplicationDto;
import kz.djdegens.academic.dtos.QuizDto;
import kz.djdegens.academic.entities.Lesson;
import kz.djdegens.academic.entities.QuizCompletion;
import kz.djdegens.academic.entities.User;
import kz.djdegens.academic.mappers.QuizMapper;
import kz.djdegens.academic.services.interfaces.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

    private final QuizMapper quizMapper;
    private final QuizData quizData;
    private final LessonData lessonData;
    private final UserData userData;
    private final LessonCompletionData lessonCompletionData;


    @Override
    public void addQuiz(QuizDto quizDto) {
        Lesson lesson = lessonData.findById(quizDto.getLessonId());
        quizData.save(quizMapper.dtoToEntity(quizDto, lesson));
    }

    @Override
    public void startAttempt(Long quizId, ApplicationDto applicationDto) {
        if(Objects.isNull(quizId))throw new IllegalArgumentException("Quiz id can not be null");
        if(Objects.isNull(applicationDto.getUser()))throw new IllegalArgumentException("User can not be null");
        if(Objects.isNull(applicationDto.getLessonCompletionDto()))throw new IllegalArgumentException("Lesson completion can not be null");
        User user = userData.findById(applicationDto.getUser().getId());
        if(user.getRole().equals("instructor"))throw new RuntimeException("Instructor can not start course");
        QuizCompletion quizCompletion = new QuizCompletion();
        quizCompletion.setUser(user);
        quizCompletion.setQuiz(quizData.findById(quizId));
        quizCompletion.setLessonCompletion(lessonCompletionData.findById(applicationDto.getLessonCompletionDto().getId()));

    }
}
