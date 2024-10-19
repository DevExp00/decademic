package kz.djdegens.academic.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuestionDto {

    private Long id;

    private String question;

    private Integer points;

    private Boolean isMultiple;

    private Long quizId;

    private List<AnswerDto> answers;
}
