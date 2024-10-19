package kz.djdegens.academic.datas.implementatios;

import kz.djdegens.academic.datas.interfaces.LessonCompletionData;
import kz.djdegens.academic.entities.LessonCompletion;
import kz.djdegens.academic.repositories.LessonCompletionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LessonCompletionDataImpl implements LessonCompletionData {

    private final LessonCompletionRepository lessonCompletionRepository;

    @Override
    public LessonCompletion save(LessonCompletion lessonCompletion) {
        if(Objects.isNull(lessonCompletion))throw new IllegalArgumentException("Lesson completion can not be null");
        return lessonCompletionRepository.save(lessonCompletion);
    }

    @Override
    public LessonCompletion findById(Long lessonCompletionId) {
        if(Objects.isNull(lessonCompletionId))throw new IllegalArgumentException("Lesson completion id can not be null");
        Optional<LessonCompletion> optionalLessonCompletion = lessonCompletionRepository.findById(lessonCompletionId);
        if(optionalLessonCompletion.isEmpty())throw new NoSuchElementException("Lesson completion not found");
        return optionalLessonCompletion.get();
    }
}
