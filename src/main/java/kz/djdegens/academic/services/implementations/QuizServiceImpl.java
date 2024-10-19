package kz.djdegens.academic.services.implementations;

import kz.djdegens.academic.datas.interfaces.*;
import kz.djdegens.academic.dtos.AnswerDto;
import kz.djdegens.academic.dtos.ApplicationDto;
import kz.djdegens.academic.dtos.QuestionDto;
import kz.djdegens.academic.dtos.QuizDto;
import kz.djdegens.academic.entities.*;
import kz.djdegens.academic.mappers.QuizMapper;
import kz.djdegens.academic.services.interfaces.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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


    @Override
    public ApplicationDto addQuiz(QuizDto quizDto) {
        Lesson lesson = lessonData.findById(quizDto.getLessonId());
        Quiz quiz = quizData.save(quizMapper.dtoToEntity(quizDto, lesson));
        quizDto = new QuizDto();
        quizDto.setId(quiz.getId());
        return ApplicationDto.builder()
                .quiz(quizDto)
                .build();
    }

    @Override
    public void startAttempt(Long quizId, ApplicationDto applicationDto) {
        if(Objects.isNull(quizId))throw new IllegalArgumentException("Quiz id can not be null");
        if(Objects.isNull(applicationDto.getUser()))throw new IllegalArgumentException("User can not be null");
        if(Objects.isNull(applicationDto.getLessonCompletion()))throw new IllegalArgumentException("Lesson completion can not be null");
        User user = userData.findById(applicationDto.getUser().getId());
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
        List<Question> questions = questionData.findAllByQuizId(quizId);
        List<QuestionDto> questionDtos = new ArrayList<>();
        for(Question question : questions){
            QuestionDto questionDto = new QuestionDto();
            questionDto.setQuestion(question.getQuestion() == null ? null : question.getQuestion());
            questionDto.setIsMultiple(question.getIsMultiple() == null ? null : question.getIsMultiple());
            questionDto.setPoints(question.getPoints() == null ? null : question.getPoints());
            List<AnswerDto> answerDtos = new ArrayList<>();
            for(Answer answer : answerData.findAllByQuestionId(question.getId())){
                AnswerDto answerDto = new AnswerDto();
                answerDto.setId(answer.getId());
                answerDto.setAnswer(answer.getAnswer() == null ? null : answer.getAnswer());
                answerDto.setIsCorrect(answer.getIsCorrect() == null ? null : answer.getIsCorrect());
                answerDtos.add(answerDto);
            }
            questionDto.setAnswers(answerDtos);
            questionDtos.add(questionDto);
        }
        QuizDto quizDto = quizMapper.entityToDto(quiz);
        return ApplicationDto.builder()
                .quiz(quizDto)
                .questions(questionDtos)
                .build();
    }
}
