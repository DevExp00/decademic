package kz.djdegens.academic.datas;

import kz.djdegens.academic.entities.Lesson;
import kz.djdegens.academic.repositories.LessonRepository;
import kz.djdegens.academic.repositories.specifications.LessonSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class LessonData {
    private final LessonRepository lessonRepository;

    public Lesson save(Lesson lesson) {
        if(Objects.isNull(lesson))throw new IllegalArgumentException("Lesson can not be null");
        return lessonRepository.save(lesson);
    }

    public Lesson findById(Long lessonId) {
        if(Objects.isNull(lessonId))throw new IllegalArgumentException("Lesson id can not be null");
        Optional<Lesson> optionalLesson = lessonRepository.findById(lessonId);
        if(optionalLesson.isEmpty())throw new NoSuchElementException("Lesson not found");
        return optionalLesson.get();
    }

    public List<Lesson> findAllByModuleId(Long moduleId) {
        if(Objects.isNull(moduleId))throw new IllegalArgumentException("Module id can not be null");
        return lessonRepository.findAll(Specification.where(LessonSpecification.findByModuleIdAndIsActive(moduleId,true)));
    }

}
