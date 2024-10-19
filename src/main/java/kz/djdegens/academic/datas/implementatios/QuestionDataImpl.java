package kz.djdegens.academic.datas.implementatios;

import kz.djdegens.academic.datas.interfaces.QuestionData;
import kz.djdegens.academic.entities.Question;
import kz.djdegens.academic.repositories.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionDataImpl implements QuestionData {

    private final QuestionRepository questionRepository;
    @Override
    public Question save(Question question) {
        if(Objects.isNull(question))throw new IllegalArgumentException("Question can not be null");
        return questionRepository.save(question);
    }

    @Override
    public Question findById(Long questionId) {
        if(Objects.isNull(questionId))throw new IllegalArgumentException("Question id can not be null");
        Optional<Question> optionalQuestion = questionRepository.findById(questionId);
        if(optionalQuestion.isEmpty())throw new NoSuchElementException("Question not found");
        return optionalQuestion.get();
    }

    @Override
    public List<Question> findAllByQuizId(Long quizId) {
        if(Objects.isNull(quizId))throw new IllegalArgumentException("Quiz id can not be null");
        return questionRepository.findAllByQuizId(quizId);
    }
}
