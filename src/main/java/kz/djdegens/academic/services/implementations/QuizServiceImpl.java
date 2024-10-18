package kz.djdegens.academic.services.implementations;

import kz.djdegens.academic.datas.interfaces.LessonData;
import kz.djdegens.academic.datas.interfaces.QuizData;
import kz.djdegens.academic.dtos.QuizDto;
import kz.djdegens.academic.entities.Lesson;
import kz.djdegens.academic.mappers.QuizMapper;
import kz.djdegens.academic.services.interfaces.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

    private final QuizMapper quizMapper;
    private final QuizData quizData;
    private final LessonData lessonData;


    @Override
    public void addQuiz(QuizDto quizDto) {
        Lesson lesson = lessonData.findById(quizDto.getLessonId());
        quizData.save(quizMapper.dtoToEntity(quizDto, lesson));
    }
}
