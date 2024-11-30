package kz.djdegens.academic.services.interfaces;

import kz.djdegens.academic.dtos.ApplicationDto;
import kz.djdegens.academic.dtos.QuestionDto;
import kz.djdegens.academic.entities.Question;

public interface QuestionService {

    void addQuestion(ApplicationDto application);

    void editQuestion(Long questionId, ApplicationDto applicationDto);

    ApplicationDto attemptQuestion(ApplicationDto applicationDto);
}
