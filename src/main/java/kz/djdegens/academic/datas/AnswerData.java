package kz.djdegens.academic.datas;

import kz.djdegens.academic.entities.Answer;
import kz.djdegens.academic.repositories.AnswerRepository;
import kz.djdegens.academic.repositories.specifications.AnswerSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AnswerData {

    private final AnswerRepository answerRepository;

    public Answer save(Answer answer) {
        if(Objects.isNull(answer))throw new IllegalArgumentException("Answer can not be null");
        return answerRepository.save(answer);
    }

    public Answer findCorrectAnswerByQuestionId(Long questionId) {
        if(Objects.isNull(questionId))throw new IllegalArgumentException("Question id can not be null");
        return answerRepository.findByQuestionIdAndIsCorrect(questionId, true);
    }

    public List<Answer> findAllByQuestionId(Long questionId) {
        if(Objects.isNull(questionId))throw new IllegalArgumentException("Question id can not be null");
        return answerRepository.findAll(Specification.where(AnswerSpecification.findByQuestionIdAndIsActive(questionId,true)));
    }

    public Answer findById(Long answerId) {
        if(Objects.isNull(answerId))throw new IllegalArgumentException("Answer id can not be null");
        Optional<Answer> optional = answerRepository.findById(answerId);
        if(optional.isEmpty())throw new NoSuchElementException("Answer not found by id :" + answerId);
        return optional.get();
    }
}
