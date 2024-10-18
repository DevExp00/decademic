package kz.djdegens.academic.datas.implementatios;

import kz.djdegens.academic.datas.interfaces.LessonData;
import kz.djdegens.academic.entities.Lesson;
import kz.djdegens.academic.repositories.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class LessonDataImpl implements LessonData {
    private final LessonRepository lessonRepository;

    @Override
    public Lesson save(Lesson lesson) {
        if(Objects.isNull(lesson))throw new IllegalArgumentException("Lesson can not be null");
        return lessonRepository.save(lesson);
    }
}
