package kz.djdegens.academic.datas.interfaces;

import kz.djdegens.academic.entities.Answer;

import java.util.List;

public interface AnswerData {

    Answer save(Answer answer);

    Answer findCorrectAnswerByQuestionId(Long questionId);

    List<Answer> findAllByQuestionId(Long questionId);
}
