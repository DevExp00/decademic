package kz.djdegens.academic.services.interfaces;

import kz.djdegens.academic.dtos.AnswerDto;
import kz.djdegens.academic.dtos.ApplicationDto;

public interface AnswerService {

    ApplicationDto addAnswer(AnswerDto answerDto);

    void editAnswer(AnswerDto answerDto);
}
