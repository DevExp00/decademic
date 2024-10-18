package kz.djdegens.academic.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuestionDto {

    private String question;

    private Integer points;

    private Boolean isMultiple;

    private Long quizId;
}
