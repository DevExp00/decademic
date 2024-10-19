package kz.djdegens.academic.datas.interfaces;

import kz.djdegens.academic.entities.Quiz;

import java.util.List;

public interface QuizData {

    Quiz save(Quiz quiz);

    Quiz findById(Long quizId);

}
