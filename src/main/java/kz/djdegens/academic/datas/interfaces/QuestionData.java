package kz.djdegens.academic.datas.interfaces;


import kz.djdegens.academic.entities.Question;

public interface QuestionData {

    Question save(Question question);

    Question findById(Long questionId);
}
