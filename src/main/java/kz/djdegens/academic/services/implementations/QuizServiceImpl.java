package kz.djdegens.academic.services.implementations;

import kz.djdegens.academic.datas.*;
import kz.djdegens.academic.dtos.AnswerDto;
import kz.djdegens.academic.dtos.ApplicationDto;
import kz.djdegens.academic.dtos.QuestionDto;
import kz.djdegens.academic.dtos.QuizDto;
import kz.djdegens.academic.entities.*;
import kz.djdegens.academic.mappers.QuizMapper;
import kz.djdegens.academic.services.interfaces.KeycloakService;
import kz.djdegens.academic.services.interfaces.QuestionService;
import kz.djdegens.academic.services.interfaces.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

    private final QuizMapper quizMapper;
    private final QuizData quizData;
    private final QuestionData questionData;
    private final LessonData lessonData;
    private final UserData userData;
    private final LessonCompletionData lessonCompletionData;
    private final QuizCompletionData quizCompletionData;
    private final AnswerData answerData;
    private final QuestionService questionService;
    private final KeycloakService keycloakService;


    @Override
    public ApplicationDto addQuiz(QuizDto quizDto) {
        Lesson lesson = lessonData.findById(quizDto.getLessonId());
        Quiz quiz = quizData.save(quizMapper.dtoToEntity(quizDto, lesson));
        return ApplicationDto.builder()
                .quiz(QuizDto.builder()
                        .id(quiz.getId())
                        .build())
                .build();
    }

    @Override
    public void startAttempt(Long quizId, ApplicationDto applicationDto) {
        if(Objects.isNull(quizId))throw new IllegalArgumentException("Quiz id can not be null");
        if(Objects.isNull(applicationDto.getUser()))throw new IllegalArgumentException("User can not be null");
        if(Objects.isNull(applicationDto.getLessonCompletion()))throw new IllegalArgumentException("Lesson completion can not be null");
        User user = userData.findByTelegramIdAndRole(Long.valueOf(keycloakService.getPreferredUsername()),"student");
        if(user.getRole().equals("instructor"))throw new RuntimeException("Instructor can not start course");
        QuizCompletion quizCompletion = new QuizCompletion();
        quizCompletion.setUser(user);
        quizCompletion.setQuiz(quizData.findById(quizId));
        quizCompletion.setLessonCompletion(lessonCompletionData.findById(applicationDto.getLessonCompletion().getId()));
        quizCompletionData.save(quizCompletion);

    }

    @Override
    public ApplicationDto getQuiz(Long quizId) {
        Quiz quiz = quizData.findById(quizId);
        QuizDto quizDto = quizMapper.entityToDto(quiz);
        return ApplicationDto.builder()
                .quiz(quizDto)
                .build();
    }

    @Override
    public void editQuiz(Long quizId, QuizDto quizDto) {
        Quiz quiz = quizData.findById(quizId);
        quizData.save(quizMapper.dtoToEntity(quiz,quizDto));
    }
}
