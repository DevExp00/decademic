package kz.djdegens.academic.services.interfaces;

import kz.djdegens.academic.dtos.ApplicationDto;
import kz.djdegens.academic.dtos.QuizDto;

public interface QuizService {

    void addQuiz(ApplicationDto applicationDto);

    void editQuiz(Long quizId, QuizDto quizDto);

    void startAttempt(Long quizId, ApplicationDto applicationDto);

    ApplicationDto getQuiz(Long quizId);
}
