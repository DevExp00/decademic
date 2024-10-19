package kz.djdegens.academic.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApplicationDto {

    private ResultDto result;
    private UserDto user;
    private CourseDto course;
    private ModuleDto module;
    private LessonDto lesson;
    private QuizDto quiz;
    private QuestionDto question;
    private AnswerDto answer;
    private CourseCompletionDto courseCompletion;
    private ModuleCompletionDto moduleCompletion;
    private LessonCompletionDto lessonCompletion;
    private QuizCompletionDto quizCompletion;
    private TeamDto teamDto;

    private List<CourseDto> courses;
    private List<ModuleDto> modules;
    private List<LessonDto> lessons;
    private List<QuizDto> quizzes;
    private List<QuestionDto> questions;
    private List<AnswerDto> answers;
}
