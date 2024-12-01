package kz.djdegens.academic.datas;

import kz.djdegens.academic.entities.Quiz;
import kz.djdegens.academic.repositories.QuizRepository;
import kz.djdegens.academic.repositories.specifications.QuizSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class QuizData {

    private final QuizRepository quizRepository;

    public Quiz save(Quiz quiz) {
        if(Objects.isNull(quiz))throw new IllegalArgumentException("Quiz can not be null");
        return quizRepository.save(quiz);
    }

    public Quiz findById(Long quizId) {
        if(Objects.isNull(quizId))throw new IllegalArgumentException("Quiz id can not be null");
        Optional<Quiz> optionalQuiz = quizRepository.findById(quizId);
        if(optionalQuiz.isEmpty())throw new NoSuchElementException("Quiz not found");
        return optionalQuiz.get();
    }

    public List<Quiz> findAllByLessonId(Long lessonId) {
        if(Objects.isNull(lessonId))throw new IllegalArgumentException("Lesson id can not be null");
        return quizRepository.findAll(Specification.where(QuizSpecification.findByLessonIdAndIsActive(lessonId,true)));
    }
}
