package kz.djdegens.academic.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuizDto {

    private String title;

    private String pointsToPass;

    private Integer order;

    private Long lessonId;

    private List<QuestionDto> questions;
}
