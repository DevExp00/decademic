package kz.djdegens.academic.mappers;

import kz.djdegens.academic.dtos.QuizDto;
import kz.djdegens.academic.entities.Lesson;
import kz.djdegens.academic.entities.Quiz;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
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

    public List<QuizDto> entityToDto(List<Quiz> quizzes){
        List<QuizDto> quizDtos = new ArrayList<>();
        for(Quiz quiz : quizzes){
            QuizDto quizDto = new QuizDto();
            quizDto.setId(quiz.getId());
            quizDto.setTitle(quiz.getTitle() == null ? null : quiz.getTitle());
            quizDto.setOrder(quiz.getOrder()==null ? null : quiz.getOrder());
            quizDto.setPointsToPass(quiz.getPointsToPass() == null ? null : String.valueOf(quiz.getPointsToPass()));
            quizDtos.add(quizDto);
        }
        return quizDtos;
    }

    public QuizDto entityToDto(Quiz quiz){
        if(Objects.isNull(quiz))throw new IllegalArgumentException("Quiz can not be null");
        QuizDto quizDto = new QuizDto();
        quizDto.setTitle(quiz.getTitle() == null ? null : quiz.getTitle());
        quizDto.setOrder(quiz.getOrder()==null ? null : quiz.getOrder());
        quizDto.setPointsToPass(quiz.getPointsToPass() == null ? null : String.valueOf(quiz.getPointsToPass()));
        return quizDto;
    }

    public Quiz dtoToEntity(Quiz quiz, QuizDto quizDto){
        if(Objects.isNull(quiz))throw new IllegalArgumentException("Quiz can not be null");
        if(Objects.isNull(quizDto))throw new IllegalArgumentException("Quiz dto can not be null");
        quiz.setTitle(quizDto.getTitle() == null ? quiz.getTitle() : quizDto.getTitle());
        quiz.setOrder(quizDto.getOrder() ==null ? quiz.getOrder() : quizDto.getOrder());
        quiz.setPointsToPass(quizDto.getPointsToPass() == null ? quiz.getPointsToPass() : Integer.valueOf(quizDto.getPointsToPass()));
        return quiz;
    }
}
