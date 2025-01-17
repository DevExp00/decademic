package kz.djdegens.academic.services.interfaces;

import kz.djdegens.academic.dtos.ApplicationDto;
import kz.djdegens.academic.dtos.LessonDto;
import kz.djdegens.academic.entities.QuizCompletion;

public interface LessonService {

    ApplicationDto addLesson(LessonDto lessonDto);

    void startAttempt(Long lessonId, ApplicationDto applicationDto);

    void handleLessonCompletion(QuizCompletion quizCompletion);

    ApplicationDto getLesson(Long lessonId);

}
