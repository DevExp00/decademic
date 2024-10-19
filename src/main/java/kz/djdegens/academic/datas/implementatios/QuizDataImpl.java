package kz.djdegens.academic.datas.implementatios;

import kz.djdegens.academic.datas.interfaces.QuizData;
import kz.djdegens.academic.entities.Quiz;
import kz.djdegens.academic.repositories.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuizDataImpl implements QuizData {

    private final QuizRepository quizRepository;

    @Override
    public Quiz save(Quiz quiz) {
        if(Objects.isNull(quiz))throw new IllegalArgumentException("Quiz can not be null");
        return quizRepository.save(quiz);
    }

    @Override
    public Quiz findById(Long quizId) {
        if(Objects.isNull(quizId))throw new IllegalArgumentException("Quiz id can not be null");
        Optional<Quiz> optionalQuiz = quizRepository.findById(quizId);
        if(optionalQuiz.isEmpty())throw new NoSuchElementException("Quiz not found");
        return optionalQuiz.get();
    }

    @Override

    public List<Quiz> findAllByLessonId(Long lessonId) {
        if(Objects.isNull(lessonId))throw new IllegalArgumentException("Lesson id can not be null");
        return quizRepository.findAllByLessonId(lessonId);
    }
}
