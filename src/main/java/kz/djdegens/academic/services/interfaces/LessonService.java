package kz.djdegens.academic.services.interfaces;

import kz.djdegens.academic.dtos.ApplicationDto;
import kz.djdegens.academic.dtos.LessonDto;

public interface LessonService {

    void addLesson(LessonDto lessonDto);

    void startAttempt(Long lessonId, ApplicationDto applicationDto);
}
