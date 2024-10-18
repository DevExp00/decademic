package kz.djdegens.academic.mappers;

import kz.djdegens.academic.dtos.AnswerDto;
import kz.djdegens.academic.entities.Answer;
import kz.djdegens.academic.entities.Question;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class AnswerMapper {

    public Answer dtoToEntity(AnswerDto answerDto, Question question){
        if(Objects.isNull(answerDto))throw new IllegalArgumentException("Answer dto can not be null");
        if(Objects.isNull(question))throw new IllegalArgumentException("Question can not be null");
        Answer answer = new Answer();
        answer.setAnswer(answerDto.getAnswer() == null ? null : answerDto.getAnswer());
        answer.setQuestion(question);
        answer.setIsCorrect(answerDto.getIsCorrect() == null ? null : answerDto.getIsCorrect());
        return answer;
    }
}
