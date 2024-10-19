package kz.djdegens.academic.services.implementations;

import kz.djdegens.academic.datas.interfaces.AnswerData;
import kz.djdegens.academic.datas.interfaces.QuestionData;
import kz.djdegens.academic.datas.interfaces.QuizCompletionData;
import kz.djdegens.academic.datas.interfaces.QuizData;
import kz.djdegens.academic.dtos.AnswerDto;
import kz.djdegens.academic.dtos.ApplicationDto;
import kz.djdegens.academic.dtos.QuestionDto;
import kz.djdegens.academic.dtos.QuizCompletionDto;
import kz.djdegens.academic.entities.Answer;
import kz.djdegens.academic.entities.Question;
import kz.djdegens.academic.entities.Quiz;
import kz.djdegens.academic.entities.QuizCompletion;
import kz.djdegens.academic.mappers.QuestionMapper;
import kz.djdegens.academic.services.interfaces.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    @Override
    public void addQuestion(QuestionDto questionDto) {
        if(Objects.isNull(questionDto))throw new IllegalArgumentException("Question dto can not be null");
        Quiz quiz = quizData.findById(questionDto.getQuizId());
        questionData.save(questionMapper.dtoToEntity(questionDto,quiz));
    }

    @Override
    public ApplicationDto attemptQuestion(ApplicationDto applicationDto) {
        if(Objects.isNull(applicationDto.getQuestions()))throw new IllegalArgumentException("Questions can not be null");
        if(Objects.isNull(applicationDto.getQuiz()))throw new IllegalArgumentException("Quiz can not be null");
        if(Objects.isNull(applicationDto.getUser()))throw new IllegalArgumentException("User can not be null");
        List<QuestionDto> questionDtos = applicationDto.getQuestions();
        QuizCompletion quizCompletion = quizCompletionData.findByQuizIdAndUserId(applicationDto.getQuiz().getId(), applicationDto.getUser().getId());
        Quiz quiz = quizData.findById(quizCompletion.getQuiz().getId());
        Integer points = quizCompletion.getScore();
        for(QuestionDto questionDto : questionDtos){
            Question question = questionData.findById(questionDto.getId());
            for(AnswerDto answerDto : questionDto.getAnswers()){
                Answer correctAnswer = answerData.findCorrectAnswerByQuestionId(question.getId());
                if(correctAnswer.getId().equals(answerDto.getId())) points += question.getPoints();
            }
        }
        if(points>=quizCompletion.getScore()){
            quizCompletion.setScore(points);
        }
        if(quiz.getPointsToPass()<=points){
            quizCompletion.setIsPassed(true);
        }
        quizCompletion.setCompletedAt(new Date());
        quizCompletionData.save(quizCompletion);

        Integer lessonScore = quizCompletion.getLessonCompletion().getScore();
        quizCompletion.getLessonCompletion().setScore(lessonScore + points);//todo
        return ApplicationDto.builder()
                .quizCompletionDto(QuizCompletionDto.builder()
                        .score(quizCompletion.getScore())
                        .isPassed(quizCompletion.getIsPassed())
                        .completedAt(quizCompletion.getCompletedAt().toString())
                        .build())
                .build();
    }
}
