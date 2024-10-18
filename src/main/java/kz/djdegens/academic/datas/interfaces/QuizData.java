package kz.djdegens.academic.datas.interfaces;

import kz.djdegens.academic.entities.Quiz;

public interface QuizData {

    Quiz save(Quiz quiz);

    Quiz findById(Long quizId);
}
