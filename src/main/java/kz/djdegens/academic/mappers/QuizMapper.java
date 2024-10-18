package kz.djdegens.academic.mappers;

import kz.djdegens.academic.dtos.QuizDto;
import kz.djdegens.academic.entities.Lesson;
import kz.djdegens.academic.entities.Quiz;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class QuizMapper {

    public Quiz dtoToEntity(QuizDto quizDto, Lesson lesson){
        if(Objects.isNull(quizDto))throw new IllegalArgumentException("Quiz dto can not be null");
        if(Objects.isNull(lesson))throw new IllegalArgumentException("Lesson can not be null");
        Quiz quiz = new Quiz();
        quiz.setTitle(quizDto.getTitle() == null ? null : quizDto.getTitle());
        quiz.setLesson(lesson);
        quiz.setOrder(quizDto.getOrder()==null ? null : quizDto.getOrder());
        quiz.setPointsToPass(quizDto.getPointsToPass() == null ? null : Integer.valueOf(quizDto.getPointsToPass()));
        return quiz;
    }
}
