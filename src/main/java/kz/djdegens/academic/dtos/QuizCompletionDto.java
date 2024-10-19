package kz.djdegens.academic.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuizCompletionDto {

    private Long id;

    private Integer score;

    private Boolean isPassed;

    private String completedAt;

}
