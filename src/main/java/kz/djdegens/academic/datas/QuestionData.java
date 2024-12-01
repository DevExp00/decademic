package kz.djdegens.academic.datas;

import kz.djdegens.academic.entities.Question;
import kz.djdegens.academic.repositories.QuestionRepository;
import kz.djdegens.academic.repositories.specifications.QuestionSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class QuestionData {

    private final QuestionRepository questionRepository;

    public Question save(Question question) {
        if(Objects.isNull(question))throw new IllegalArgumentException("Question can not be null");
        return questionRepository.save(question);
    }

    public Question findById(Long questionId) {
        if(Objects.isNull(questionId))throw new IllegalArgumentException("Question id can not be null");
        Optional<Question> optionalQuestion = questionRepository.findById(questionId);
        if(optionalQuestion.isEmpty())throw new NoSuchElementException("Question not found");
        return optionalQuestion.get();
    }

    public List<Question> findAllByQuizId(Long quizId) {
        if(Objects.isNull(quizId))throw new IllegalArgumentException("Quiz id can not be null");
        return questionRepository.findAll(Specification.where(QuestionSpecification.findByQuizIdAndIsActive(quizId,true)));
    }
}
