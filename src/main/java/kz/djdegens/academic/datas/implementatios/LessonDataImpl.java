package kz.djdegens.academic.datas.implementatios;

import kz.djdegens.academic.datas.interfaces.LessonData;
import kz.djdegens.academic.entities.Lesson;
import kz.djdegens.academic.repositories.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LessonDataImpl implements LessonData {
    private final LessonRepository lessonRepository;

    @Override
    public Lesson save(Lesson lesson) {
        if(Objects.isNull(lesson))throw new IllegalArgumentException("Lesson can not be null");
        return lessonRepository.save(lesson);
    }

    @Override
    public Lesson findById(Long lessonId) {
        if(Objects.isNull(lessonId))throw new IllegalArgumentException("Lesson id can not be null");
        Optional<Lesson> optionalLesson = lessonRepository.findById(lessonId);
        if(optionalLesson.isEmpty())throw new NoSuchElementException("Lesson not found");
        return optionalLesson.get();
    }

    @Override
    public List<Lesson> findAllByModuleId(Long moduleId) {
        if(Objects.isNull(moduleId))throw new IllegalArgumentException("Module id can not be null");
        return lessonRepository.findAllByModuleId(moduleId);
    }
}
