package kz.djdegens.academic.datas.interfaces;

import kz.djdegens.academic.entities.LessonCompletion;

public interface LessonCompletionData {

    LessonCompletion save(LessonCompletion lessonCompletion);

    LessonCompletion findById(Long lessonCompletionId);
}
