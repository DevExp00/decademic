package kz.djdegens.academic.datas.interfaces;


import kz.djdegens.academic.entities.Question;

import java.util.List;

public interface QuestionData {

    Question save(Question question);

    Question findById(Long questionId);

    List<Question> findAllByQuizId(Long quizId);
}
