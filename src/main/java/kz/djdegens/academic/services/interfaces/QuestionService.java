package kz.djdegens.academic.services.interfaces;

import kz.djdegens.academic.dtos.AnswerDto;
import kz.djdegens.academic.dtos.ApplicationDto;
import kz.djdegens.academic.dtos.QuestionDto;
import kz.djdegens.academic.entities.Question;

import java.util.List;

public interface QuestionService {

    void addQuestion(QuestionDto questionDto);

    void editQuestion(Long questionId, ApplicationDto applicationDto);

    ApplicationDto attemptQuestion(ApplicationDto applicationDto);
}
