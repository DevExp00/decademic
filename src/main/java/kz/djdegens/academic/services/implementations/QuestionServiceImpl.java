package kz.djdegens.academic.services.implementations;

import kz.djdegens.academic.datas.*;
import kz.djdegens.academic.dtos.*;
import kz.djdegens.academic.entities.*;
import kz.djdegens.academic.mappers.QuestionMapper;
import kz.djdegens.academic.services.interfaces.AnswerService;
import kz.djdegens.academic.services.interfaces.KeycloakService;
import kz.djdegens.academic.services.interfaces.LessonService;
import kz.djdegens.academic.services.interfaces.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuizData quizData;
    private final QuestionData questionData;
    private final QuestionMapper questionMapper;
    private final AnswerData answerData;
    private final QuizCompletionData quizCompletionData;
    private final UserData userData;
    private final LessonService lessonService;
    private final AnswerService answerService;
    private final KeycloakService keycloakService;

    @Override
    @Transactional
    public void addQuestion(ApplicationDto application) {
        if(Objects.isNull(application.getQuestion()))throw new IllegalArgumentException("Question dto can not be null");
        if(Objects.isNull(application.getAnswers()))throw new IllegalArgumentException("Answers dto can not be null");
        QuestionDto questionDto = application.getQuestion();
        Quiz quiz = quizData.findById(questionDto.getQuizId());
        Question question = questionData.save(questionMapper.dtoToEntity(questionDto,quiz));
        for(AnswerDto answerDto : application.getAnswers()){
            answerDto.setQuestionId(question.getId());
            answerService.addAnswer(answerDto);
        }
    }

    @Override
    public ApplicationDto attemptQuestion(ApplicationDto applicationDto) {
        if(Objects.isNull(applicationDto.getQuestions()))throw new IllegalArgumentException("Questions can not be null");
        if(Objects.isNull(applicationDto.getUser()))throw new IllegalArgumentException("User can not be null");

        QuizCompletion quizCompletion = quizCompletionData.findById(applicationDto.getQuizCompletion().getId());
        Quiz quiz = quizData.findById(quizCompletion.getQuiz().getId());
        User student = userData.findByTelegramIdAndRole(Long.valueOf(keycloakService.getPreferredUsername()),"student");
        List<QuestionDto> questionDtos = applicationDto.getQuestions();
        Integer points = quizCompletion.getScore();
        for(QuestionDto questionDto : questionDtos){
            Question question = questionData.findById(questionDto.getId());
            for(AnswerDto answerDto : questionDto.getAnswers()){
                Answer correctAnswer = answerData.findCorrectAnswerByQuestionId(question.getId());
                if(correctAnswer.getId().equals(answerDto.getId())) points += question.getPoints();
                AnswerHistory answerHistory = new AnswerHistory();
                answerHistory.setUser(student);
                answerHistory.setQuestion(question);
                answerHistory.setAnswer(correctAnswer);
                answerHistory.setQuizCompletion(quizCompletion);
                answerHistory.setQuiz(quiz);
            }
        }
        if(!quizCompletion.getIsPassed()){
            if(points>=quizCompletion.getScore() && quiz.getPointsToPass()<=points){
                quizCompletion.setScore(points);
                quizCompletion.setIsPassed(true);
                quizCompletion.setCompletedAt(new Date());
                quizCompletion.getLessonCompletion().setScore(quizCompletion.getLessonCompletion().getScore() + points);
                quizCompletionData.save(quizCompletion);
                lessonService.handleLessonCompletion(quizCompletion);
            }
        }
        return ApplicationDto.builder()
                .quizCompletion(QuizCompletionDto.builder()
                        .score(points)
                        .isPassed(quizCompletion.getIsPassed())
                        .completedAt(new Date().toString())
                        .build())
                .build();
    }

    @Override
    @Transactional
    public void editQuestion(Long questionId, ApplicationDto applicationDto){
        Question question = questionData.findById(questionId);
        for(AnswerDto answerDto : applicationDto.getAnswers()){
            answerService.editAnswer(answerDto.getId(),answerDto);
        }
        questionData.save(questionMapper.dtoToEntity(question,applicationDto.getQuestion()));
    }

    @Override
    public ApplicationDto getQuestions(Long quizId) {
        List<Question> questions = questionData.findAllByQuizId(quizId);
        List<QuestionDto> questionDtos = new ArrayList<>();
        for(Question question : questions){
            QuestionDto questionDto = new QuestionDto();
            questionDto.setId(question.getId());
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
        return ApplicationDto.builder()
                .questions(questionDtos)
                .build();
    }
}
