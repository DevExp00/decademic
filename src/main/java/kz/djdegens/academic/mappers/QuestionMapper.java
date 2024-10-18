package kz.djdegens.academic.mappers;

import kz.djdegens.academic.dtos.QuestionDto;
import kz.djdegens.academic.entities.Question;
import kz.djdegens.academic.entities.Quiz;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class QuestionMapper {

    public Question dtoToEntity(QuestionDto questionDto, Quiz quiz){
        if(Objects.isNull(questionDto))throw new IllegalArgumentException("Question dto can not be null");
        if(Objects.isNull(quiz))throw new IllegalArgumentException("Quiz can not be null");
        Question question = new Question();
        question.setQuestion(questionDto.getQuestion() == null ? null : questionDto.getQuestion());
        question.setPoints(questionDto.getPoints() == null ? null : questionDto.getPoints());
        question.setQuiz(quiz);
        question.setIsMultiple(questionDto.getIsMultiple() == null ? null : questionDto.getIsMultiple());
        return question;
    }
}
