package kz.djdegens.academic.datas.implementatios;

import kz.djdegens.academic.datas.interfaces.QuizCompletionData;
import kz.djdegens.academic.entities.QuizCompletion;
import kz.djdegens.academic.repositories.QuizCompletionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class QuizCompletionDataImpl implements QuizCompletionData {

    private final QuizCompletionRepository quizCompletionRepository;

    @Override
    public QuizCompletion save(QuizCompletion quizCompletion) {
        if(Objects.isNull(quizCompletion))throw new IllegalArgumentException("Quiz completion can not be null");
        return quizCompletionRepository.save(quizCompletion);
    }

    @Override
    public QuizCompletion findById(Long quizCompletionId) {
        if(Objects.isNull(quizCompletionId))throw new IllegalArgumentException("Quiz completion id can not be null");
        Optional<QuizCompletion> optionalQuizCompletion = quizCompletionRepository.findById(quizCompletionId);
        if(optionalQuizCompletion.isEmpty())throw new NoSuchElementException("Quiz completion not found");
        return optionalQuizCompletion.get();
    }

    @Override
    public QuizCompletion findByQuizIdAndUserId(Long quizId, Long userId) {
        if(Objects.isNull(quizId))throw new IllegalArgumentException("Quiz id can not be null");
        if(Objects.isNull(userId))throw new IllegalArgumentException("User id can not be null");
        return quizCompletionRepository.findByQuizIdAndUserId(quizId,userId);
    }
}
