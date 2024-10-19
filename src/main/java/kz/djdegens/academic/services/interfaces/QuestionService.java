package kz.djdegens.academic.services.interfaces;

import kz.djdegens.academic.dtos.ApplicationDto;
import kz.djdegens.academic.dtos.QuestionDto;
import kz.djdegens.academic.entities.Question;

public interface QuestionService {

    void addQuestion(QuestionDto questionDto);

    ApplicationDto attemptQuestion(ApplicationDto applicationDto);
}
