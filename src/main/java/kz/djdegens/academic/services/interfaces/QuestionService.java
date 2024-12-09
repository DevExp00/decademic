package kz.djdegens.academic.services.interfaces;

import kz.djdegens.academic.dtos.AnswerDto;
import kz.djdegens.academic.dtos.ApplicationDto;
import kz.djdegens.academic.dtos.QuestionDto;
import kz.djdegens.academic.entities.Question;

import java.util.List;

public interface QuestionService {

    ApplicationDto addQuestion(QuestionDto questionDto);

    void editQuestion(Long questionId,QuestionDto questionDto);

    ApplicationDto attemptQuestion(ApplicationDto applicationDto);

    ApplicationDto getQuestions(Long quizId);
}
