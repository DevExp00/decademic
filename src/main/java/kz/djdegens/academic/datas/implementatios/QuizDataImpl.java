package kz.djdegens.academic.datas.implementatios;

import kz.djdegens.academic.datas.interfaces.QuizData;
import kz.djdegens.academic.entities.Quiz;
import kz.djdegens.academic.repositories.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class QuizDataImpl implements QuizData {

    private final QuizRepository quizRepository;

    @Override
    public Quiz save(Quiz quiz) {
        if(Objects.isNull(quiz))throw new IllegalArgumentException("Quiz can not be null");
        return quizRepository.save(quiz);
    }
}
