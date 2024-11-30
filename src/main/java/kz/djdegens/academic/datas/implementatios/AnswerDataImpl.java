package kz.djdegens.academic.datas.implementatios;

import kz.djdegens.academic.datas.interfaces.AnswerData;
import kz.djdegens.academic.entities.Answer;
import kz.djdegens.academic.repositories.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnswerDataImpl implements AnswerData {

    private final AnswerRepository answerRepository;

    @Override
    public Answer save(Answer answer) {
        if(Objects.isNull(answer))throw new IllegalArgumentException("Answer can not be null");
        return answerRepository.save(answer);
    }

    @Override
    public Answer findCorrectAnswerByQuestionId(Long questionId) {
        if(Objects.isNull(questionId))throw new IllegalArgumentException("Question id can not be null");
        return answerRepository.findByQuestionIdAndIsCorrect(questionId, true);
    }

    @Override
    public List<Answer> findAllByQuestionId(Long questionId) {
        if(Objects.isNull(questionId))throw new IllegalArgumentException("Question id can not be null");
        return answerRepository.findAllByQuestionId(questionId);
    }

    @Override
    public Answer findById(Long answerId) {
        if(Objects.isNull(answerId))throw new IllegalArgumentException("Answer id can not be null");
        Optional<Answer> optional = answerRepository.findById(answerId);
        if(optional.isEmpty())throw new NoSuchElementException("Answer not found by id :" + answerId);
        return optional.get();
    }
}
