package kz.djdegens.academic.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AnswerDto {

    private String answer;

    private Long questionId;

    private Boolean isCorrect;
}
