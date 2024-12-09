package kz.djdegens.academic.datas;

import kz.djdegens.academic.entities.LessonCompletion;
import kz.djdegens.academic.entities.QuizCompletion;
import kz.djdegens.academic.repositories.QuizCompletionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;


@Component
@RequiredArgsConstructor
public class QuizCompletionData {

    private final QuizCompletionRepository quizCompletionRepository;

    public QuizCompletion save(QuizCompletion quizCompletion) {
        if(Objects.isNull(quizCompletion))throw new IllegalArgumentException("Quiz completion can not be null");
        return quizCompletionRepository.save(quizCompletion);
    }

    public QuizCompletion findById(Long quizCompletionId) {
        if(Objects.isNull(quizCompletionId))throw new IllegalArgumentException("Quiz completion id can not be null");
        Optional<QuizCompletion> optionalQuizCompletion = quizCompletionRepository.findById(quizCompletionId);
        if(optionalQuizCompletion.isEmpty())throw new NoSuchElementException("Quiz completion not found");
        return optionalQuizCompletion.get();
    }

    public QuizCompletion findByQuizIdAndUserId(Long quizId, Long userId) {
        if(Objects.isNull(quizId))throw new IllegalArgumentException("Quiz id can not be null");
        if(Objects.isNull(userId))throw new IllegalArgumentException("User id can not be null");
        return quizCompletionRepository.findByQuizIdAndUserId(quizId,userId);
    }

    public List<QuizCompletion> findAllByQuizIdAndLessonCompletionAndUserIdAndIsPassed(Long quizId, LessonCompletion lessonCompletion, Long userId, Boolean isPassed) {
        if(Objects.isNull(quizId)) throw new IllegalArgumentException("Quiz id can not be null");
        if(Objects.isNull(lessonCompletion)) throw new IllegalArgumentException("Lesson completion can not be null");
        if(Objects.isNull(userId)) throw new IllegalArgumentException("User id can not be null");
        if(Objects.isNull(isPassed)) throw new IllegalArgumentException("Is Passed can not be null");
        return quizCompletionRepository.findAllByQuizIdAndLessonCompletionAndUserIdAndIsPassed(quizId, lessonCompletion, userId, isPassed);
    }
}