package kz.djdegens.academic.datas.interfaces;

import kz.djdegens.academic.entities.Lesson;

import java.util.List;

public interface LessonData {

    Lesson save(Lesson lesson);

    Lesson findById(Long lessonId);

    List<Lesson> findAllByModuleId(Long moduleId);
}
